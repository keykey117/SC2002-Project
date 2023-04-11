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
    public User login(){

        // initialize modules

        do {
            System.out.println("\n----------------------------");
            System.out.println("\nPlease enter UserID:");
            String username = sc.nextLine();
            System.out.println("Please enter Password:");
            String password = sc.nextLine();
            System.out.println("----------------------------");

            User user = new User(username);


            // System.out.println(CredentialDB.getInstance().getPassword(username));
            if (!user.validate(password )){
                System.out.println("\nError: Invalid Credential");
                return null;
            } else {
                System.out.println("\nLogged in. Welcome " + username + ".");
                return user;
            }
        } while (true);
    }
}