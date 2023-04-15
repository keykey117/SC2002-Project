import java.util.List;

public class Student extends User {
    private Project project;
    public Student(String name, String email, String userID) {
        super(name, email, userID);
    }
    public void viewAvailableProjects() {
        // Implement logic to return a list of available projects
        List<Project> projects = FYPDB.getInstance().getProjects();
        for (Project project: projects) {
            if (project.getStatus() == ProjectStatus.AVAILABLE) {
                System.out.println(project.toString());
            }
        }
    }

    public void requestProjectAllocation(Project project) {
        // Implement logic to send request to the FYP coordinator to allocate the project
    }

    public void requestTitleChange(String newTitle) {
        // Implement logic to send request to the supervisor to change the project title
    }

    public void requestDeregistration() {
        // Implement logic to send request to the FYP coordinator to deregister from the allocated project
    }

    public List<Request> viewRequestHistory() {
        // Implement logic to return a list of requests made by the student
    }
}
