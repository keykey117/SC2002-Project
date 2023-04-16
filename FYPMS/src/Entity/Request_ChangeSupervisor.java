package Entity;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
import Controller.ProjectController;
import Controller.Supervisor;
import Enum.*;
import Database.*;

/**
 * Represents a request to change the supervisor of a project.
 * It extends the Request class.
 *  @author Tham Key Yew
 *  @version 1.0.0 Apr 16, 2023
 */
public class Request_ChangeSupervisor extends Request {
    /**
     * The ID of the replacement supervisor.
     */
    private String replacementSupervisor;

    /**
     * Constructs a new Request_ChangeSupervisor object with the specified parameters.
     *
     * @param SenderID            the ID of the sender of the request
     * @param ReceiverID          the ID of the receiver of the request
     * @param reqType             the type of the request
     * @param projectID           the ID of the project associated with the request
     * @param replacementSupervisor the ID of the replacement supervisor
     */
    public Request_ChangeSupervisor(String SenderID, String ReceiverID, RequestType reqType, int projectID, String replacementSupervisor) {
        super(SenderID, ReceiverID, reqType, projectID);
        this.replacementSupervisor = replacementSupervisor;
    }

    /**
     * Approves the request and changes the project's supervisor to the replacement supervisor.
     */
    public void approve() {
        super.approve();
        FYPDB fypdb = FYPDB.getInstance();
        Supervisor newSupervisor = fypdb.getSupervisor(this.replacementSupervisor);
        Supervisor oldSupervisor = fypdb.getSupervisor(this.getSenderID());
        ProjectController projectController = new ProjectController();
        projectController.changeSupervisor(this.getProjectID(),newSupervisor);

        // check whether NEW supervisor has more than 2 projects
        newSupervisor.addProjCount();
        newSupervisor.makeProjectUnavailable();


        // check whether OLD supervisor has less than 2 projects
        oldSupervisor.subProjCount();
        oldSupervisor.makeProjectAvailable();
    }

    /**
     * Rejects the request.
     */
    public void reject() {
        super.reject();
    }
}
