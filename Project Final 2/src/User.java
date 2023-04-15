import java.util.ArrayList;
import java.util.List;
public class User {
    private String name;
    private String email;
    private String userID;
    private String password;

    private List<Request> incoming_requests;

    private List<Request> outgoing_requests;



    public User(String name, String email, String userID) {
        this.name = name;
        this.email = email;
        this.userID = userID;
        this.password = "password";
        this.incoming_requests = new ArrayList<>();
        this.outgoing_requests = new ArrayList<>();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String emailAddress) {
        this.email = emailAddress;
    }

    public String getID(){
        return this.userID;
    }

    public String getPassword(){
        return this.password;
    }

    public boolean login(String userID, String password) {
        return this.userID.equals(userID) && this.password.equals(password);
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public void addIncomingRequest(Request req) {
        incoming_requests.add(req);
    }

    public void addOutgoingRequest(Request req) {
        outgoing_requests.add(req);
    }

    public List<Request> GetIncomingRequest() {
        return incoming_requests;
    }
    public List<Request> GetOutgoingRequest() {
        return outgoing_requests;
    }
}
