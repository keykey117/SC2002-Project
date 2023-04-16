package UI;


import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Controller.Student;
import Entity.Project;
import Database.*;
import Enum.ProjectStatus;

/**
 * This represents the Student Module which will be accessed when the user logs in using a Student Account
 *
 * Upon succesful login as Student, the user will be presented with options available to a Student
 *
 * @author Ian Sim, Tan Ding Jiang
 * @version 1.0.0 Apr 15, 2023
 */
public class StudentModule {
    /**
     * Valid Student account needed
     */
    private Student student;

    /**
     * Creates a new StudentModule
     * @param student   required parameter for accessing student's information for later methods
     */
    public StudentModule(Student student){
        this.student = student;
    }

    /**
     * Displays the menu and redirects user
     */
    public void run(){
        Scanner sc = new Scanner(System.in);

        FYPDB fypdb = FYPDB.getInstance();

    // Choices student can make
        int choice = -1;
        while (choice != 8) {
            System.out.println("\n--------------Student Panel--------------");
            System.out.println("Option Available: (1-8):");
            System.out.println("(1) Change Password");
            System.out.println("(2) View available projects");
            System.out.println("(3) Request Project to send to Coordinator");
            System.out.println("(4) View My Project");
            System.out.println("(5) Request Project Title change");
            System.out.println("(6) View request status and history");
            System.out.println("(7) Deregister FYP");
            System.out.println("(8) Quit Student Panel");
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
            List<Project> projects;
            switch(choice){

                case 1: {
                    System.out.println("Enter old password: ");
                    String oldPassword = sc.nextLine();
                    if (!oldPassword.equals(student.getPassword())) {
                        System.out.println("Password does not match");
                        break;
                    }
                    System.out.println("Enter new password: ");
                    String newPassword = sc.nextLine();
                    student.changePassword(newPassword);
                    break;
                }

                case 2:
                    student.viewAvailableProjects();
                    break;
                case 3:
                    if(student.getDeregistered()){
                        System.out.println("You are not allowed to make selection again as you deregistered your FYP.");
                        break;
                    }

                    // Check outgoing request of student, if he has outgoing request that is reserved, stop him from registering
                    if(student.checkReserved()){
                        System.out.println("You have already reserved one project.");
                        break;
                    }

                    System.out.println("Enter AVAILABLE projectID that you wish to register:");
                    try {
                        int projectID = sc.nextInt();
                        sc.nextLine();
                        Project project = fypdb.getProjectByID(projectID);
                        if (project == null || project.getStatus() != ProjectStatus.AVAILABLE){
                            System.out.println("You have entered an invalid project ID");
                            break;
                        }
                        student.requestProjectAllocation(project);
                        System.out.println("Your request have been successfully submitted!");
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid project ID.");
                        sc.nextLine();
                        break;
                    }
                    break;
                case 4:
                    try {
                        System.out.println(student.getProject().toString());
                    }
                    catch(Exception e){
                        System.out.println("You have no project registered");
                    }
                    break;
                case 5:
                    if(student.getProject() == null){
                        System.out.println("You do not have a registered project");
                        break;
                    }
                    System.out.println("Enter the new title you wish to change:");
                    String newTitle = sc.nextLine();
                    student.requestTitleChange(newTitle);
                    System.out.println("Your request have been successfully submitted!");
                    break;
                case 6:
                    student.PrintAllRequest();
                    break;
                case 7:
                    student.requestDeregistration();
                    System.out.println("Your deregistration request have been successfully submitted!");
                    break;
                case 8:
                    System.out.println("Signing out of student module...");
                    break;
                default: System.out.println("Invalid choice!");
            }
        }
        
    }
}
