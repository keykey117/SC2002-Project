package UI;

import java.util.Scanner;
import Database.*;
public class FYPManagementSystem {

    public static void main(String[] args) {
        FYPDB.getInstance();
        Scanner sc = new Scanner(System.in);

        LoginModule loginModule = new LoginModule();
        System.out.println("Welcome to our FYPManager program");
        int loginChoice = 0;

        while(loginChoice != 2){
            System.out.println("\n----------Login Panel----------");
            System.out.println("(1) Login");
            System.out.println("(2) Quit ");
            System.out.println("-------------------------------");
            System.out.print("\nChoice: ");


            try{
                loginChoice = sc.nextInt();
            } catch (Exception err){
                System.out.println("Error: Please input a valid number (1/2).\n");
                sc.nextLine();
                continue;
            }

            sc.nextLine();
            switch (loginChoice) {
                case 1 -> loginModule.run();
                case 2 -> System.out.println("Exiting system...");
                default -> System.out.println("Invalid Choice.");
            }
        }
    
    }

//    private void loadSystemData() {
//        // Call the methods in the SystemInitializer class to load data from the provided xls files
//        this.students = SystemInitializer.initializeStudentList("students.txt");
//        this.supervisors = SystemInitializer.initializeFacultyList("supervisor.txt");
//        this.projects = SystemInitializer.initializeProjectList("projects.txt");
//        this.fypCoordinator = SystemInitializer.initializeFYPCoordinatorList("FYPCoordinators.txt");
//    }

    // private void handleUserInput() {
    //     // Implement logic to process user input and perform corresponding actions based on the input
    //     // Need to implement student interface
    //     System.out.println
    // }
}
