public class Request_DeregisterFYP extends Request {
    private int projectID;

    public Request_DeregisterFYP(String SenderID, String ReceiverID, RequestType reqType, int projectID) {
        super(SenderID, ReceiverID, reqType);
        this.projectID = projectID;
    }
}
