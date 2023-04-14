import java.util.Scanner;

public interface Password {
    /**
     * Creates a Scanner object which allows for user inputs
     */
    Scanner sc = new Scanner(System.in);
    /**
     * Default method to change password for Student, Supervisor and FYPCoordinator
     */
    default void setPassword(String userID) {
        System.out.println("Enter old password:");
        String oldPassword = sc.nextLine();
        System.out.println("Enter new password:");
        String newPassword = sc.nextLine();

        if(CredentialDB.getInstance().getCredential(userID).setPassword(oldPassword, newPassword)){
            System.out.println("Your password for " + userID + " has been successfully changed");
        }
        else {
            System.out.println("Entered the wrong old password");
        };
    }
}
