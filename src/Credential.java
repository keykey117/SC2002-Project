/**
 * Contains the individual credentials of each account
 */
public class Credential {
    //Attributes
    /**
     * The username of the account in String datatype
     */
    private String username;
    /**
     * The password of the account in String datatype
     */
    private String password;
    /**
     * The role of the account in String datatype
     */
    private String role;

    //Constructor
    /**
     * Creates a new Credential for a new Admin account
     * @param username this Credential's new username
     * @param password this Credential's new password
     * @param role this Credential's new role
     */
    public Credential(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    //Operations
    /**
     * Gets this Credential's username
     * @return this Credential's username in String datatype
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Gets this Credential's role
     * @return this Credential's role in AdminRole datatype
     */
    public String getRole() {
        return this.role;
    }


    //Change password
    /**
     * Changes the password of this Credential if the old password provided is correct
     * @param oldPassword the user input old password for verification
     * @param newPassword this Credential's new password
     * @return a boolean true if password is successfully changed and false if password is not changed due to oldPassword not matching the current password of Credential
     */
    public boolean setPassword(String oldPassword, String newPassword) {
        //User needs to provide the correct old password to change password
        //To do: implement password checks to ensure that password is 8 char, alphanumeric, contains capital letters and symbols
        if (this.password.equals(oldPassword)) {
            this.password = newPassword;
            return true;
        } else {
            return false;
        }
    }

    //Check if correct password
    /**
     * Check function to see if user input password matches the password of Credential
     * @param password user input password to be checked against the password attribute of Credential
     * @return a boolean true if passwords match, false if they do not
     */
    public boolean check(String password) {

        return this.password.equals(password);
    }

    /**
     * Gets the password of Credential
     * @return this Credential's password in String datatype
     */
    public String getPassword() {
        return password;
    }
}


