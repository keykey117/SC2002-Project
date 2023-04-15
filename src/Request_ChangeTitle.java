//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class Request_ChangeTitle extends Request {
    private String newTitle;

    public Request_ChangeTitle(String SenderID, String ReceiverID, RequestType reqType, int ProjectID, String newTitle) {
        super(SenderID, ReceiverID, reqType, ProjectID);
        this.newTitle = newTitle;
    }

    public void approve() {
        super.approve();
    }

    public void reject() {
        super.reject();
    }
}
