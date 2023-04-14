import java.util.ArrayList;

public abstract class User implements Password, ProjectHandler {
    protected String name;
    protected String email;
    protected String userID;

    // constructor
    public User(String name, String email, String userID) {
        this.name = name;
        this.email = email;
        this.userID = userID;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getUserID() {
        return this.userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // implement the methods from the Password and ProjectHandler interfaces here
}