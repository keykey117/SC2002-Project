package UI;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;

import Controller.FYPCoordinator;
import Entity.*;
import Enum.*;
/**
 * The FYPCoordinatorModule class is a User Interface class that provides a command-line interface
 * for FYP Coordinator's functionalities.
 * It allows FYP Coordinator to view all projects, pending requests, approve or reject requests,
 * and generate a project details report.
 *
 * @author Tan Ding Jiang
 * @version 1.0.0 Apr 16, 2023
 */

public class FYPCoordinatorModule{
    private FYPCoordinator fypCoordinator;

    /**
     * Constructs a FYPCoordinatorModule with the specified FYPCoordinator.
     *
     * @param fypCoordinator the FYPCoordinator to associate with the FYPCoordinatorModule.
     */
    public FYPCoordinatorModule(FYPCoordinator fypCoordinator){
        this.fypCoordinator = fypCoordinator;
    }

    /**
     * Runs the FYPCoordinatorModule's command-line interface, allowing FYP Coordinator to view all projects,
     * pending requests, approve or reject requests, and generate a project details report.
     */
    public void run(){
        Scanner sc = new Scanner(System.in);

        // Choices supervisor can make
        int choice = -1;
        while (choice != 5) {
            System.out.println("\n--------------FYP Coordinator Panel--------------");
            System.out.println("Option Available: (1-5):");
            System.out.println("(1) View all projects");
            if(this.fypCoordinator.hasPendingRequests()){
                System.out.println("(2) View all pending request (NEW)");
            }
            else {
                System.out.println("(2) View all pending request");
            }
            if(this.fypCoordinator.hasPendingRequests()){
                System.out.println("(3) Approve/Reject Requests (NEW)");
            }
            else {
                System.out.println("(3) Approve/Reject Requests");
            }
            System.out.println("(4) Filter and Generate Project Details Report");
            System.out.println("(5) Quit FYP Panel");
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

            switch(choice){
                case 1:
                    fypCoordinator.viewAllProjects();
                    break;
                case 2:
                    fypCoordinator.printPendingRequests();
                    break;
                case 3:
                    // Show only pending
                    System.out.println("Enter Request Type you want to see");
                    System.out.println("Option Available: (1-4):");
                    System.out.println("(1) Change Title");
                    System.out.println("(2) Deregister FYP");
                    System.out.println("(3) Register FYP");
                    System.out.println("(4) Change Supervisor");

                    int inputRequestType = 0;

                    while (true) {
                        try {
                            System.out.println("Please enter a number between 1 and 4: ");
                            inputRequestType = sc.nextInt();
                            sc.nextLine();

                            if (inputRequestType >= 1 && inputRequestType <= 4) {
                                break;
                            } else {
                                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a number between 1 and 4.");
                            sc.nextLine();
                        }
                    }

                    RequestType requestType;

                    switch(inputRequestType){
                        case 1:
                            requestType = RequestType.CHANGE_TITLE;
                            break;
                        case 2:
                            requestType = RequestType.DEREGISTER_FYP;
                            break;
                        case 3:
                            requestType = RequestType.REGISTER_FYP;
                            break;
                        default:
                            requestType = RequestType.CHANGE_SUPERVISOR;
                            break;
                    }

                    if(!fypCoordinator.getPendingRequestOfType(requestType).isEmpty()) {
                        //View Change Supervisor Requests
                        fypCoordinator.viewRequestOfType(requestType);

                        // Prompt for which request to approve/reject
                        System.out.println("Enter requestID of Change FYP supervisor request you want to approve/reject");
                        int supRequestID = sc.nextInt();
                        sc.nextLine();
                        // Returns the list of incoming supervisor change requests for the fypcoordinator
                        List<Request> requests = fypCoordinator.getPendingRequestOfType(requestType);
                        for (Request request : requests) {
                            if (request.getReqID() == supRequestID) {
                                System.out.println("Do you want to approve (Y/N)");
                                String approve = sc.nextLine();
                                if (approve.equals("Y")) {
                                    request.approve();
                                    System.out.println("Request has been approved");
                                } else if (approve.equals("N")) {
                                    request.reject();
                                    System.out.println("Request has been rejected");
                                } else {
                                    System.out.println("Invalid input");
                                }
                            }
                        }
                    }
                    else{
                            System.out.println("No projects for this request type found");
                        }

                    break;
                case 4:
                    fypCoordinator.generateProjectDetailsReport();
                    break;
                case 5:
                    System.out.println("Signing out of student module...");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }

        }
    }
}