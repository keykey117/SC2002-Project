package Entity;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
import Enum.*;
import Database.*;

public class Request_ChangeTitle extends Request {
    private String newTitle;

    public Request_ChangeTitle(String SenderID, String ReceiverID, RequestType reqType, int ProjectID, String newTitle) {
        super(SenderID, ReceiverID, reqType, ProjectID);
        this.newTitle = newTitle;
    }

    public void approve() {
        super.approve();
        FYPDB fypdb = FYPDB.getInstance();
        Project project = fypdb.getProjectByID(this.getProjectID());
        project.setTitle(newTitle);

    }

    public void reject() {
        super.reject();
    }
}
