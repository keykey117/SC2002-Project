public class Request {
    public static int numRequest = 0;
    private int reqID;
    private String senderID;
    private String receiverID;
    private RequestStatus reqStatus;

    private RequestType reqType;

    public Request(String SenderID, String ReceiverID, RequestType reqType) {
        this.senderID = SenderID;
        this.receiverID = ReceiverID;
        this.reqType = reqType;
        this.reqStatus = RequestStatus.PENDING;
        this.reqID = ++numRequest;
    }
    public int getReqID() {
        return reqID;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }

    public RequestStatus isReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(RequestStatus reqStatus) {
        this.reqStatus = reqStatus;
    }

    public RequestType getReqType() {
        return reqType;
    }

    public void setReqType(RequestType reqType) {
        this.reqType = reqType;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Request " + reqID);
        if (reqType == RequestType.CHANGE_TITLE) {
            sb.append(" to change title");
        }
        else if (reqType == RequestType.REGISTER_FYP) {
            sb.append(" to register FYP");
        }
        else if (reqType == RequestType.DEREGISTER_FYP) {
            sb.append(" to deregister FYP");
        }
        if (reqStatus == RequestStatus.PENDING) {
            sb.append(" is pending");
        }
        else if (reqStatus == RequestStatus.APPROVED) {
            sb.append(" is approved");
        }
        else if  (reqStatus == RequestStatus.REJECTED) {
            sb.append(" is rejected");
        }
        sb.append("\n");
        return sb.toString();
    }

}
