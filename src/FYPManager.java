import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FYPManager {
    /**
     * This is the entry point for the FYPManager program
     */
    public static void main(String[] args) {

            /**
             * Creates a new scanner to accept user input
             */
            Scanner sc = new Scanner(System.in);

            // testing this
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