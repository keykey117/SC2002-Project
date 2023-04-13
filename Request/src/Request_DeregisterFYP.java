public class Request_DeregisterFYP extends Request {
    private String projectID;

    public Request_DeregisterFYP(String SenderID, String ReceiverID, RequestType reqType, String projectID) {
        super(SenderID, ReceiverID, reqType);
        this.projectID = projectID;
    }
}
