package Entity;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
import Controller.ProjectController;
import Controller.Student;
import Controller.Supervisor;
import Enum.*;
import Database.*;

/**
 * This class represents a request to register for a final year project (FYP).
 * It extends the Request class.
 *  @author Tham Key Yew
 *  @version 1.0.0 Apr 16, 2023
 */
public class Request_RegisterFYP extends Request {

    /**
     * Constructs a new Request_RegisterFYP object with the given parameters.
     *
     * @param SenderID    the ID of the sender of the request
     * @param ReceiverID  the ID of the receiver of the request
     * @param reqType     the type of the request
     * @param projectID   the ID of the FYP project to register for
     */
    public Request_RegisterFYP(String SenderID, String ReceiverID, RequestType reqType, int projectID) {
        super(SenderID, ReceiverID, reqType, projectID);
        FYPDB fypdb = FYPDB.getInstance();
        Project project = fypdb.getProjectByID(this.getProjectID());
        Student student = fypdb.getStudent(SenderID);
        project.setStudent(student);
    }

    /**
     * Approves the request by registering the student for the FYP project.
     */
    public void approve() {
        super.approve();
        FYPDB fypdb = FYPDB.getInstance();
        Student student = fypdb.getStudent(this.getSenderID());

        //mark project as allocated
        Project project = fypdb.getProjectByID(this.getProjectID());
        project.setStatus(ProjectStatus.ALLOCATED);
        project.setStudent(student);

        //allocate project to student
        student.setProject(project);

        // check whether supervisor has more than 2 projects
        Supervisor supervisor = fypdb.getSupervisor(project.getSupervisor().getID());
        supervisor.addProjCount();
        supervisor.makeProjectUnavailable();
    }

    /**
     * Rejects the request by setting the FYP project status to AVAILABLE.
     */
    public void reject() {
        super.reject();
        FYPDB fypdb = FYPDB.getInstance();
        Project project = fypdb.getProjectByID(this.getProjectID());
        project.setStatus(ProjectStatus.AVAILABLE);
    }
}
