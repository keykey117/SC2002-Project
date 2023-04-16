package Entity;
import Enum.RequestStatus;

import java.util.ArrayList;
import java.util.List;
/**
 * Represents a User superclass.
 * The class will implement the interfaces Person, ReviewHandler, MovieQuery and TicketInterface
 *
 * @author Tan Ding Jiang, Tham Key Yew
 * @version 1.0.0 Apr 15, 2023
 */
public class User {
    /**
     * The user's Name in String datatype
     */
    private String name;
    /**
     * The user's email in String datatype
     */
    private String email;
    /**
     * The user's userID in String datatype
     */
    private String userID;
    /**
     * The user's password in String datatype
     */
    private String password;
    /**
     * The user's incoming_request in ArrayList containing Request objects
     */
    private List<Request> incoming_requests;
    /**
     * The user's outgoing_request in ArrayList containing Request objects
     */
    private List<Request> outgoing_requests;

    /**
     * Constructor for user, obtaining name, email and UserID from .txt file (handled by SystemInitializer)
     * @param name      new name
     * @param email     new email
     * @param userID    new userID
     */
    public User(String name, String email, String userID) {
        this.name = name;
        this.email = email;
        this.userID = userID;
        this.password = "password";

        this.incoming_requests = new ArrayList<>();
        this.outgoing_requests = new ArrayList<>();
    }
    /**
     * Gets this User's name
     * @return the name of the User
     */
    public String getName() {
        return name;
    }
    /**
     * Sets this User's name
     * @param name new name of the User
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets this User's email
     * @return the email of the User
     */
    public String getEmail() { return email; }

    /**
     * Sets this User's email
     * @param emailAddress ew email of the User
     */
    public void setEmail(String emailAddress) {
        this.email = emailAddress;
    }

    /**
     * Gets this user's ID
     * @return the userID of User
     */
    public String getID(){
        return this.userID;
    }

    /**
     * Get this User's password
     * @return the password of the User
     */
    public String getPassword(){
        return this.password;
    }

    /**
     * Changes this User's password
     * @param newPassword new password for the User
     */
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    /**
     * Adds Request to incomingRequest
     * @param req new Request
     */
    public void addIncomingRequest(Request req) {
        incoming_requests.add(req);
    }

    /**
     * Adds Request to outgoingRequest
     * @param req new Request
     */
    public void addOutgoingRequest(Request req) {
        outgoing_requests.add(req);
    }

    /**
     * Get incoming_requests of the User
     * @return an Arraylist of all the incoming Request of the User
     */
    public List<Request> getIncomingRequest() {
        return incoming_requests;
    }
    /**
     * Get outgoing_requests of the User
     * @return an Arraylist of all the outgoing Request of the User
     */
    public List<Request> getOutgoingRequest() {
        return outgoing_requests;
    }

    /**
     * Get specific Incoming Request based on the reqID
     * @param reqID - reqID of the specific req
     * @return Request - the User's request as specified by reqID
     */
    public Request getSpecificIncomingRequest(int reqID){
        for (Request request: this.getIncomingRequest()){
            if (request.getReqID() == reqID){
                return request;
            }
        }
        return null;
    }
    /**
     * Get specific Outgoing Request based on the reqID
     * @param reqID - reqID of the specific req
     * @return Request - the User's request as specified by reqID
     */
    public Request getSpecificOutgoingRequest(int reqID){
        for (Request request: this.getOutgoingRequest()){
            if (request.getReqID() == reqID){
                return request;
            }
        }
        return null;
    }

    /**
     * Displays all the pending requests as part of the User
     */
    public void printPendingRequests() {
        List<Request> requests = this.getIncomingRequest();
        for (int i = 0; i < requests.size(); i++) {
            if (requests.get(i).getReqStatus() == RequestStatus.PENDING) {
                System.out.println(requests.get(i).toString());
            }
        }

    }

    /**
     * Abstract method for overriding for subclasses
     */
    public void PrintAllRequest() {
    }

}
