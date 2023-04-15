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
        return null;
    }

    public List<Request> viewPendingRequests() {
        // Implement logic to return a list of pending requests sent by supervisors and students
        return null;
    }

    public void approveRequest(Request request) {
        // Implement logic to approve the request sent by a supervisor or a student
    }

    public void rejectRequest(Request request) {
        // Implement logic to reject the request sent by a supervisor or a student
    }

    public List<Request> viewRequestHistory() {
        // Implement logic to return a list of all requests handled by the FYP coordinator
        return null;
    }
}

