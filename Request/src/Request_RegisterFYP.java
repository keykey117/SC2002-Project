public class Request_RegisterFYP extends Request{

    public Request_RegisterFYP(String SenderID, String ReceiverID, RequestType reqType, int projectID) {
        super(SenderID, ReceiverID, reqType, projectID);
    }
    
    @Override
    public void approve() {
        super.approve();
        //assign FYP to student
        //FYP is marked as allocated
    }

    @Override
    public void reject() {
        super.reject();
        //return FYP to available
    }
}
