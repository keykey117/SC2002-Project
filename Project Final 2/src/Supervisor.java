import java.util.ArrayList;
import java.util.List;



public class Supervisor extends User {
    private List<Project> projects;
    private int projCount;
    private final int MAXPROJECTS = 2;

    public Supervisor(String name, String email, String userID) {
        super(name, email, userID);
        this.projects = new ArrayList<>();
        this.projCount = 0;
    }

    public void createProject(String title) {
        List<Project> projectList = FYPDB.getInstance().getProjects();
        int projectID = projectList.size() + 1;
        Supervisor supervisor = this;
        Project project = new Project(projectID, supervisor, title);
        projectList.add(project);

    }

    public List<Project> getProjects() {
        return projects;
    }

    public void viewProjects(){
        List<Project> projects = FYPDB.getInstance().getProjects();
        for (Project project : projects){
            if(project.getSupervisor().getID().equals(this.getID())){
                System.out.println(project.toString());
            }
        }
    }

    public void modifyTitle(Project project, String newTitle) {
        project.setTitle(newTitle);
    }


    public void requestStudentTransfer(Project project, Supervisor newSupervisor, FYPCoordinator fypCoordinator) {
        // Implement the logic to send a request to the FYP coordinator to transfer a student to a replacement supervisor.
        Request request = new Request_ChangeSupervisor(this.getID(), fypCoordinator.getID(), RequestType.CHANGE_TITLE, project.getProjectID(), newSupervisor.getID());
        fypCoordinator.addIncomingRequest(request);
        this.addOutgoingRequest(request);
    }

    public void PrintPendingRequestsFromStudents() {
        // Implement the logic to return a list of pending requests from students.
        List<Request> requests = this.GetIncomingRequest();
        for (int i = 0; i < requests.size(); i++) {
            if (requests.get(i).getReqStatus() == RequestStatus.PENDING) {
                requests.get(i).toString();
            }
        }
    }

    public void approveRequest_TitleChange(Request request) {
        // Implement the logic to approve the request made by a student.
        request.approve();
        //TODO find Project object and change its title.
    }

    public void rejectRequest(Request request) {
        // Implement the logic to reject the request made by a student.
        request.reject();
    }

    public void PrintAllRequest() {
        // Implement the logic to return a list of requests handled by the supervisor.
        System.out.println("ALL INCOMING REQUESTS:\n");
        List<Request> incomingRequests = this.GetIncomingRequest();
        for (int i = 0; i < incomingRequests.size(); i++) {
            incomingRequests.get(i).toString();
        }
        System.out.println("ALL OUTGOING REQUESTS:\n");
        List<Request> outgoingRequests = this.GetOutgoingRequest();
        for (int i =0; i < outgoingRequests.size(); i++) {
            outgoingRequests.get(i).toString();
        }

    }


    // Getter and setter methods for the attributes
    public void addAllProjects(){

    }

}
