import java.util.ArrayList;
import java.util.List;

public class Supervisor extends User {
    private List<Project> projects;
    private List<Request> requests;
    private int projCount;
    private final int MAXPROJECTS = 2;

    public Supervisor(String name, String email, String userID) {
        super(name, email, userID);
        this.projects = new ArrayList<>();
        this.requests = new ArrayList<>();
        this.projCount = 0;
    }

    public void createProject(String title) {
        List<Project> projectList = FYPDB.getInstance().getProjects();
        int projectID = projectList.size() + 1;
        Supervisor supervisor = this;
        Project project = new Project(projectID, supervisor, title);
        projectList.add(project);
        projCount++;
    }

    public List<Project> viewProjects() {
        return projects;
    }

    public void modifyTitle(Project project, String newTitle) {
        project.setTitle(newTitle);
    }

    public void requestStudentTransfer(Project project, Supervisor newSupervisor) {
        // Implement the logic to send a request to the FYP coordinator to transfer a student to a replacement supervisor.
    }

    public List<Request> viewPendingRequests() {
        // Implement the logic to return a list of pending requests from students.
    }

    public void approveRequest(Request request) {
        // Implement the logic to approve the request made by a student.
    }

    public void rejectRequest(Request request) {
        // Implement the logic to reject the request made by a student.
    }

    public List<Request> viewRequestHistory() {
        // Implement the logic to return a list of requests handled by the supervisor.
    }

    // Getter and setter methods for the attributes
}
