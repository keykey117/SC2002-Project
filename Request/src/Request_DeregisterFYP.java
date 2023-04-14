public class Request_DeregisterFYP extends Request {
    private int projectID;

    public Request_DeregisterFYP(String SenderID, String ReceiverID, RequestType reqType, int projectID) {
        super(SenderID, ReceiverID, reqType);
        this.projectID = projectID;
    }

    @Override
    public void approve() {
        super.approve();
        //deregister project from SenderID
    }

    @Override
    public void reject() {
        super.reject();
        //only for FYP coordinator
    }
}
