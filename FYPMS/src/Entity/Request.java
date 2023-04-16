package Entity;
import Controller.ProjectController;
import Enum.RequestStatus;
import Enum.RequestType;

/**
 * This class represents a Request object which is used to request changes to a Project's properties.
 * The Request object stores the details of the request such as the SenderID, ReceiverID, RequestType,
 * RequestStatus, RequestID and ProjectID.
 * @author Tham Key Yew
 * @version 1.0.0 Apr 16, 2023
 */
public class Request {
    /**
     * A static variable that keeps track of the total number of requests made
    */
    public static int numRequest = 0;
    private int reqID;
    private String senderID;
    private String receiverID;
    private int projectID;
    private RequestStatus reqStatus;
    private RequestType reqType;
    /**
     * Constructor for creating a new Request object.
     * @param SenderID the ID of the user who is sending the request.
     * @param ReceiverID the ID of the user who is receiving the request.
     * @param reqType the type of request being made.
     * @param ProjectID the ID of the project associated with the request.
     */
    public Request(String SenderID, String ReceiverID, RequestType reqType, int ProjectID) {
        this.senderID = SenderID;
        this.receiverID = ReceiverID;
        this.reqType = reqType;
        this.reqStatus = RequestStatus.PENDING;
        this.reqID = ++numRequest;
        this.projectID = ProjectID;
    }
    /**
     * Gets the unique ID of the request.
     * @return the unique ID of the request
     */
    public int getReqID() {
        return this.reqID;
    }
    /**
     * Sets the unique ID of the request.
     * @param reqID the unique ID of the request
     */
    public void setReqID(int reqID) {
        this.reqID = reqID;
    }

    /**
     * Gets the ID of the user who made the request.
     * @return the ID of the user who made the request
     */
    public String getSenderID() {
        return this.senderID;
    }

    /**
     * Gets the projectID of the request .
     * @return the projectID of the request
     */
    public int getProjectID() {
        return this.projectID;
    }
    /**
     * Sets the ID of the FYP project associated with the request.
     * @param projectID the ID of the FYP project associated with the request
     */
    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }
    /**
     * Returns the request status of this request object.
     * @return the request status
     */
    public RequestStatus getReqStatus() {
        return this.reqStatus;
    }

    /**
     * Returns the type of the request.
     * @return the type of the request
     */
    public RequestType getReqType() {
        return this.reqType;
    }

    /**
     * Returns a string representation of the FYPRequest object, including the request ID,
     * request type, and request status.
     * @return a string representation of the FYPRequest object
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("REQUEST ID: ").append(this.reqID).append('\n');
        sb.append("REQUEST STATUS: ");
        if (this.reqStatus == RequestStatus.PENDING) {
            sb.append("PENDING");
        } else if (this.reqStatus == RequestStatus.APPROVED) {
            sb.append("APPROVED");
        } else if (this.reqStatus == RequestStatus.REJECTED) {
            sb.append("REJECTED");
        }
        sb.append("\n");
        sb.append("REQUEST TYPE: ");
        if (this.reqType == RequestType.CHANGE_TITLE) {
            sb.append("Change FYP title");
        } else if (this.reqType == RequestType.REGISTER_FYP) {
            sb.append("Register FYP");
        } else if (this.reqType == RequestType.DEREGISTER_FYP) {
            sb.append("Deregister FYP");
        } else if (this.reqType == RequestType.CHANGE_SUPERVISOR) {
            sb.append("Change FYP supervisor");
        }
        sb.append('\n');
        sb.append("FROM: ").append(this.senderID).append('\n');
        sb.append("TO: ").append(this.receiverID).append('\n');
        ProjectController projectController = new ProjectController();
        sb.append(projectController.getProjectByID(this.projectID).toString());
        sb.append('\n');
        return sb.toString();
    }
    /**
     * Approves the request by setting its status to "approved".
     * will be overridden by specific types of request
     */
    public void approve() {
        this.reqStatus = RequestStatus.APPROVED;
    }
    /**
     * Rejects the request by setting its status to "rejected".
     * will be overridden by specific types of request
     */
    public void reject() {
        this.reqStatus = RequestStatus.REJECTED;
    }
}
