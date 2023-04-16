package Entity;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
import Controller.ProjectController;
import Enum.*;
import Database.*;
/**
 * Represents a request to change the title of a project.
 * It extends the Request class.
 *  @author Tham Key Yew
 *  @version 1.0.0 Apr 16, 2023
 */
public class Request_ChangeTitle extends Request {
    /**
     * The new title of the project.
     */
    private String newTitle;


    /**
     * Constructs a new Request_ChangeTitle object with the specified parameters.
     *
     * @param SenderID   the ID of the sender of the request
     * @param ReceiverID the ID of the receiver of the request
     * @param reqType    the type of the request
     * @param ProjectID  the ID of the project associated with the request
     * @param newTitle   the new title of the project
     */
    public Request_ChangeTitle(String SenderID, String ReceiverID, RequestType reqType, int ProjectID, String newTitle) {
        super(SenderID, ReceiverID, reqType, ProjectID);
        this.newTitle = newTitle;
    }

    /**
     * Approves the request and changes the project's title to the new title.
     */
    public void approve() {
        super.approve();
        ProjectController projectController = new ProjectController();
        projectController.changeTitle(this.getProjectID(), newTitle);

    }

    /**
     * Rejects the request.
     */
    public void reject() {
        super.reject();
    }
}
