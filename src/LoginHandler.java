import java.util.Scanner;

/**
 *
 * @author dingjiang
 * @version 1.0.0 Nov 13, 2022
 */
public class LoginHandler {
    /**
     * Creates a Scanner object to receive user input
     */
    Scanner sc = new Scanner(System.in);

    /**
     * Main login function which prompts user for username and password before verifying the password before allowing further user access.
     * @return Student/Supervisor/FYP Coordinator module depending on the user
     */
    public void login(){
        //user input

        do {
            System.out.println("\n----------------------------");
            System.out.println("\nPlease enter UserID:");
            String username = sc.nextLine();
            System.out.println("Please enter Password:");
            String password = sc.nextLine();
            System.out.println("----------------------------");


            // System.out.println(CredentialDB.getInstance().getPassword(username));
            // todo encapsulate this class into a user module OR check role and load the different modules from here
            if (!password.equals(CredentialDB.getInstance().getPassword(username))){
                System.out.println("\nError: Invalid Credential");
                return;
            } else {
                System.out.println("\nLogged in. Welcome " + username + ".");
                return;
            }
        } while (true);
    }
}