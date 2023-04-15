//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class Request_DeregisterFYP extends Request {
    public Request_DeregisterFYP(String SenderID, String ReceiverID, RequestType reqType, int projectID) {
        super(SenderID, ReceiverID, reqType, projectID);
    }

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

    public void reject() {
        super.reject();
    }
}
