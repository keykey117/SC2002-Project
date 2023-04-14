public class Request_RegisterFYP extends Request{
    private int projectID;

    public Request_RegisterFYP(String SenderID, String ReceiverID, RequestType reqType, int projectID) {
        super(SenderID, ReceiverID, reqType);
        this.projectID = projectID;
    }


    @Override
    public void approve() {
        super.approve();
        //add FYP to student and FYP is allocated
    }

    @Override
    public void reject() {
        super.reject();
        //return FYP to available
    }
}
