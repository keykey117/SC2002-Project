import java.util.List;
import java.util.Map;

public class FYPCoordinator extends Supervisor {
    public FYPCoordinator(String name, String email, String userID) {
        super(name, email, userID);
    }
    public void changeProjectSupervisor(Project project, Supervisor newSupervisor) {
        // Implement logic to change the supervisor of a project upon request
    }

    public void allocateProjectToStudent(Project curProject, Student student) {
        // Implement logic to allocate a project to a student upon request
        int projectID = curProject.getProjectID();
        List<Project> projectList = FYPDB.getInstance().getProjects();
        for(Project project : projectList){
            if(project.getProjectID() == projectID){
                project.setStudent(student);
                project.setStatus(ProjectStatus.ALLOCATED);
            }
        }
    }

    public void deregisterStudentFromProject(Student student, Project project) {
        // Implement logic to deregister a student from a project upon request
    }


    public void viewAllProjects() {
        List<Project> projects = FYPDB.getInstance().getProjects();
        for (Project project : projects){
            System.out.println(project.toString());
        }
        return;
    }

    public List<Project> generateProjectDetailsReport(Map<String, String> filters) {
        // Implement logic to generate a project details report based on the provided filters
    }

    public void printPendingRequests() {
        // Implement logic to return a list of pending requests sent by supervisors and students
        List<Request> requests = this.GetIncomingRequest();
        for (int i = 0; i < requests.size(); i++) {
            if (requests.get(i).getReqStatus() == RequestStatus.PENDING) {
                requests.get(i).toString();
            }
        }

    }

    public void approve(Request request) {
        // Implement logic to approve the request sent by a supervisor or a student
        request.approve();
        //implement in Request
    }

    public void rejectRequest(Request request) {
        // Implement logic to reject the request sent by a supervisor or a student
        request.reject();
    }

    public void PrintAllRequest() {
        // Implement logic to return a list of all requests handled by the FYP coordinator
        System.out.println("ALL INCOMING REQUESTS:\n");
        List<Request> incomingRequests = this.GetIncomingRequest();
        for (int i = 0; i < incomingRequests.size(); i++) {
            incomingRequests.get(i).toString();
        }
    }


}

