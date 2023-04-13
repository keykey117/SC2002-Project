public class Request_RegisterFYP extends Request{
    private String projectID;

    public Request_RegisterFYP(String SenderID, String ReceiverID, RequestType reqType, String projectID) {
        super(SenderID, ReceiverID, reqType);
        this.projectID = projectID;
    }
}
