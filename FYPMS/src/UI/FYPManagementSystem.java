package UI;

import java.util.Scanner;
import Database.*;
/**
 * Main function and landing page for users. Users will be able to select if they would like to login or quit
 * @author Tan Ding Jiang
 * @version 1.0.0 Apr 16, 2023
 */
public class FYPManagementSystem {
    /**
     * This is the entry point for the FYP Management System program
     */
    public static void main(String[] args) {
        /**
         * Loads the DB upon starting program
         */
        FYPDB.getInstance();
        Scanner sc = new Scanner(System.in);
        /**
         * Loads a new LoginModule upon startup
         */
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
}
