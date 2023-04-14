public class Request_ChangeTitle extends Request{
    private String newTitle;

    public Request_ChangeTitle(String SenderID, String ReceiverID, RequestType reqType, String newTitle) {
        super(SenderID, ReceiverID, reqType);
        this.newTitle = newTitle;
    }

    //Only supervisor can approve or reject title change
    @Override
    public void approve() {
        super.approve();
        //change project title
    }

    @Override
    public void reject() {
        super.reject();
    }
}
