package UI;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Controller.FYPCoordinator;
import Entity.Project;
import Controller.Supervisor;
import Entity.*;
import Database.*;
/**
 * This represents the Supervisor Module which will be accessed when the user logs in using a Supervisor Account
 *
 * Upon successful login as Supervisor, the user will be presented with options available to a Supervisor
 *
 * @author Ian Sim, Tan Ding Jiang
 * @version 1.0.0 Apr 15, 2023
 */
public class SupervisorModule {
    /**
     * Valid Supervisor account needed
     */
    private Supervisor supervisor;

    /**
     * Creates a new SupervisorModule
     * @param supervisor - required parameter for accessing student's information for later methods
     */
    public SupervisorModule(Supervisor supervisor){
        this.supervisor = supervisor;
    }

    /**
     * Displays the menu and redirects user
     */
    public void run(){
        Scanner sc = new Scanner(System.in);

        // Choices supervisor can makex
        int choice = -1;
        while (choice != 9) {
            System.out.println("\n--------------Supervisor Panel--------------");
            System.out.println("Option Available: (1-6):");
            System.out.println("(1) Change Password");
            System.out.println("(2) View own Projects");
            System.out.println("(3) Modify own Project Title");
            System.out.println("(4) Add new Project");
            System.out.println("(5) Request to change Supervisor");
            System.out.println("(6) Approve/Reject Requests");
            System.out.println("(7) View all requests made");
            System.out.println("(8) Enter FYP Module (ONLY FYPCOORDINATOR)");
            System.out.println("(9) Quit Supervisor Panel");
            System.out.println("---------------------------------------");
            System.out.print("\nChoice: ");


            try{
                choice = sc.nextInt();
            } catch (InputMismatchException err){
                System.out.println("Error: Please input a valid number (1 - 6).\n");
                sc.nextLine();
                continue;
            }
            sc.nextLine();
            switch(choice) {
                case 1:
                    System.out.println("Enter old password: ");
                    String oldPassword = sc.nextLine();
                    if(!oldPassword.equals(supervisor.getPassword())){
                        System.out.println("Password does not match");
                        break;
                    }
                    System.out.println("Enter new password: ");
                    String newPassword = sc.nextLine();
                    supervisor.changePassword(newPassword);
                    break;
                case 2:
                    supervisor.viewProjects();
                    break;
                case 3:
                    supervisor.viewProjects();
                    System.out.println("Which project would you like to modify?");
                    int projectID = sc.nextInt();
                    String newTitle = "";

                    // check for their own project
                    List<Project> projects = FYPDB.getInstance().getProjects();
                    for(Project project : projects){
                        if(project.getProjectID() == projectID && project.getSupervisor().getID().equals(supervisor.getID())){
                            System.out.println("This is the project");
                            project.toString();
                            sc.nextLine();
                            System.out.println("Enter the new title:");
                            newTitle = sc.nextLine();
                            project.setTitle(newTitle);
                            break;
                        }
                    }
                    if (newTitle.isEmpty()) {
                        System.out.println("You have entered an invalid projectID");
                    }
                    break;
                case 4:
                    System.out.println("Enter the new project title:");
                    String projTitle = sc.nextLine();
                    supervisor.createProject(projTitle);
                    break;
                case 5:
                    try {
                        System.out.println("Enter the projectID that you want to transfer");
                        int transProjectID = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Enter the new supervisorID that you want to transfer");
                        String supervisorID = sc.nextLine();
                        List<Project> transProjects = FYPDB.getInstance().getProjects();
                        for(Project project : transProjects){
                            if(project.getProjectID() == transProjectID && project.getSupervisor().getID().equals(supervisor.getID())){
                                supervisor.requestStudentTransfer(transProjectID, supervisorID);
                                break;
                            }
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                    }


                    break;
                case 6:
                    supervisor.printPendingRequests();
                    System.out.println("Enter reqID for approval:");
                    try {
                        int reqID = sc.nextInt();
                        sc.nextLine();
                        Request request = supervisor.getSpecificIncomingRequest(reqID);
                        if (request == null){
                            System.out.println("Request not found");
                            break;
                        }
                        System.out.println("Do you want to approve (Y/N)");
                        String approve = sc.nextLine();
                        if(approve.equals("Y")){
                            request.approve();
                            System.out.println("Request has been approved");
                        }
                        else if (approve.equals("N")){
                            request.reject();
                            System.out.println("Request has been rejected");
                        }
                        else{
                            System.out.println("Invalid input");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        sc.nextLine(); // consume the invalid input
                    }
                    break;
                case 7:
                    supervisor.PrintAllRequest();
                    break;
                case 8:
                    if(supervisor instanceof FYPCoordinator){
                        FYPCoordinatorModule fypCoordinatorModule = new FYPCoordinatorModule((FYPCoordinator) supervisor);
                        fypCoordinatorModule.run();
                    }
                    else {
                        System.out.println("You are not a FYP coordinator");
                    }
                    break;
                case 9:
                    System.out.println("Signing out of supervisor module...");
                    break;
                default: 
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }
}
