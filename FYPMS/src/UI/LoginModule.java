package UI;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Controller.FYPCoordinator;
import Controller.Student;
import Controller.Supervisor;
import Database.*;
/**
 * This represents the login landing page after selecting the login option in the home page
 * User will be required to log in as part of the basic security features
 * Users can access Student, Supervisor, or FYPCoordinator
 * @author Ian Sim
 * @version 1.0.0 Apr 16, 2023
 */
public class LoginModule{
    Scanner sc = new Scanner(System.in);
    /**
     * This method is the main functionality of the LoginModule, which activates login functionalities and gives further options for the admin to use once login is verified.
     */
    public void run(){
        int userType;
        do {
            System.out.println("\n----------------------------");
            System.out.println("\nPlease enter 1 for student, 2 for Supervisor, 3 for FYPCoordinator");
            try {
                userType = sc.nextInt();
                if (userType < 1 || userType > 3) {
                    System.out.println("Invalid option. Please enter a number between 1 and 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
                sc.next(); // Consume the invalid input to prevent an infinite loop
                userType = 0; // Set to an invalid value to trigger the loop again
            }
        } while (userType < 1 || userType > 3);

        sc.nextLine();
        System.out.println("\n----------------------------");
        System.out.println("Enter your username:");
        String username = sc.nextLine();

        System.out.println("\n----------------------------");
        System.out.println("Enter your password:");
        String password = sc.nextLine();


        // Check password
        switch(userType){
            case 1:
                List<Student> studentList = FYPDB.getInstance().getStudents();
                StudentModule studentModule = null;
                for (Student student : studentList){
                    if(username.equals(student.getID()) && password.equals(student.getPassword())){
                        studentModule = new StudentModule(student);
                        studentModule.run();
                    }
                }
                if(studentModule == null){
                    System.out.println("Wrong password entered.");
                }
                break;
            case 2:
                List<Supervisor> supervisorList = FYPDB.getInstance().getSupervisors();
                SupervisorModule supervisorModule = null;
                for (Supervisor supervisor: supervisorList){
                    if(username.equals(supervisor.getID()) && password.equals(supervisor.getPassword())){
                        supervisorModule = new SupervisorModule(supervisor);
                        supervisorModule.run();
                    }
                }
                break;
            case 3:
                List<FYPCoordinator> fypCoordinatorList = FYPDB.getInstance().getFypCoordinator();
                for (FYPCoordinator fypCoordinator: fypCoordinatorList){
                    if(username.equals(fypCoordinator.getID()) && password.equals(fypCoordinator.getPassword())){
                        supervisorModule = new SupervisorModule(fypCoordinator);
                        supervisorModule.run();
                    }
                }
                break;
            default:
                System.out.println("Invalid choice!");
            }
        }
}
