//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class Request {
    public static int numRequest = 0;
    private int reqID;
    private String senderID;
    private String receiverID;
    private int projectID;
    private RequestStatus reqStatus;
    private RequestType reqType;

    public Request(String SenderID, String ReceiverID, RequestType reqType, int ProjectID) {
        this.senderID = SenderID;
        this.receiverID = ReceiverID;
        this.reqType = reqType;
        this.reqStatus = RequestStatus.PENDING;
        this.reqID = ++numRequest;
        this.projectID = ProjectID;
    }

    public int getReqID() {
        return this.reqID;
    }

    public void setReqID(int reqID) {
        this.reqID = reqID;
    }

    public String getSenderID() {
        return this.senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getReceiverID() {
        return this.receiverID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }

    public int getProjectID() {
        return this.projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public RequestStatus getReqStatus() {
        return this.reqStatus;
    }

    public void setReqStatus(RequestStatus reqStatus) {
        this.reqStatus = reqStatus;
    }

    public RequestType getReqType() {
        return this.reqType;
    }

    public void setReqType(RequestType reqType) {
        this.reqType = reqType;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("REQUEST ID: ").append(this.reqID).append('\n');
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
        sb.append("REQUEST STATUS: ");
        if (this.reqStatus == RequestStatus.PENDING) {
            sb.append("PENDING");
        } else if (this.reqStatus == RequestStatus.APPROVED) {
            sb.append("APPROVED");
        } else if (this.reqStatus == RequestStatus.REJECTED) {
            sb.append("REJECTED");
        }
        sb.append("\n");
        sb.append('\n');
        return sb.toString();
    }

    public void approve() {
        this.reqStatus = RequestStatus.APPROVED;
    }

    public void reject() {
        this.reqStatus = RequestStatus.REJECTED;
    }
}
