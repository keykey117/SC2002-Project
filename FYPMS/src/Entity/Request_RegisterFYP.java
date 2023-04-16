package Entity;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
import Controller.Student;
import Controller.Supervisor;
import Enum.*;
import Database.*;
public class Request_RegisterFYP extends Request {
    public Request_RegisterFYP(String SenderID, String ReceiverID, RequestType reqType, int projectID) {
        super(SenderID, ReceiverID, reqType, projectID);
    }

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

    public void reject() {
        super.reject();
        FYPDB fypdb = FYPDB.getInstance();
        Project project = fypdb.getProjectByID(this.getProjectID());
        project.setStatus(ProjectStatus.AVAILABLE);
    }
}
