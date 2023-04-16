import java.util.Scanner;

/**
 *
 */
public class LoginHandler {
    /**
     * Creates a Scanner object to receive user input
     */
    Scanner sc = new Scanner(System.in);

    /**
     * Main login function which prompts user for username and password before verifying the password before allowing further user access.
     * @return User module
     */
    public Credential login(){

        do {
            try {
                System.out.println("\n----------------------------");
                System.out.println("\nPlease enter UserID:");
                String username = sc.nextLine();
                System.out.println("Please enter Password:");
                String password = sc.nextLine();
                System.out.println("----------------------------");

                Credential credential = CredentialDB.getInstance().getCredential(username);

                if (credential == null){
                    System.out.println("\nError: Invalid Credential");
                }
                else if(!credential.check(password)) {
                    System.out.println("\nError: Invalid Credential");
                    return null;
                } else {
                    System.out.println("\nLogged in. Welcome " + username + ".");
                    return credential;
                }
            } catch (Exception error){
                System.out.print(error.getMessage());
            }
        } while (true);
    }
}