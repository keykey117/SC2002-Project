package Entity;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
import Controller.Student;
import Controller.Supervisor;
import Enum.*;
import Database.*;


/**
 * Represents a request to deregister a student from a project.
 * It extends the Request class.
 *  @author Tham Key Yew
 *  @version 1.0.0 Apr 16, 2023
 */
public class Request_DeregisterFYP extends Request {
    /**
     * Constructs a new Request_DeregisterFYP object with the specified parameters.
     *
     * @param SenderID   the ID of the sender of the request
     * @param ReceiverID the ID of the receiver of the request
     * @param reqType    the type of the request
     * @param projectID  the ID of the project associated with the request
     */
    public Request_DeregisterFYP(String SenderID, String ReceiverID, RequestType reqType, int projectID) {
        super(SenderID, ReceiverID, reqType, projectID);
    }
    /**
     * Approves the request and deregisters the student from the project.
     */
    public void approve() {
        super.approve();
        FYPDB fypdb = FYPDB.getInstance();
        Student student = fypdb.getStudent(this.getSenderID());
        //mark project as available
        Project project = fypdb.getProjectByID(this.getProjectID());
        project.setStatus(ProjectStatus.AVAILABLE);
        project.setStudent(null);

        //deallocate project from student
        student.setProject(null);
        student.setDeregistered();

        //check for projCount for supervisor
        Supervisor supervisor = fypdb.getSupervisor(project.getSupervisor().getID());
        supervisor.subProjCount();
        supervisor.makeProjectAvailable();
    }

    /**
     * Rejects the request.
     */
    public void reject() {
        super.reject();
    }
}
