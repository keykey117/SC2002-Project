public class Request_DeregisterFYP extends Request {
    private int projectID;

    public Request_DeregisterFYP(String SenderID, String ReceiverID, RequestType reqType, int projectID) {
        super(SenderID, ReceiverID, reqType);
        this.projectID = projectID;
    }


    //Only FYP coordinator can approve or reject
    @Override
    public void approve() {
        super.approve();
        //deregister project from SenderID
        //the student cannot reapply for the same FYP
    }

    @Override
    public void reject() {
        super.reject();

    }
}
