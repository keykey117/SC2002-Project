package Entity;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
import Controller.ProjectController;
import Controller.Supervisor;
import Enum.*;
import Database.*;

public class Request_ChangeSupervisor extends Request {
    private String replacementSupervisor;

    public Request_ChangeSupervisor(String SenderID, String ReceiverID, RequestType reqType, int projectID, String replacementSupervisor) {
        super(SenderID, ReceiverID, reqType, projectID);
        this.replacementSupervisor = replacementSupervisor;
    }

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

    public void reject() {
        super.reject();
    }
}
