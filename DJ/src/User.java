public class User {
    private String userID;

    private UserRole userRole;

    public User (String userID) {
        this.userID = userID;
        setUserRole(CredentialDB.getInstance().getRole(userID));
    }

    public boolean validate(String password){
        return password.equals(CredentialDB.getInstance().getPassword(this.userID));
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public String getUserID() {
        return userID;
    }
}
