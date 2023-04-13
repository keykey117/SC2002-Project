public class Request_ChangeTitle extends Request{
    private String newTitle;

    public Request_ChangeTitle(String SenderID, String ReceiverID, RequestType reqType, String newTitle) {
        super(SenderID, ReceiverID, reqType);
        this.newTitle = newTitle;
    }
}
