import java.util.List;

public class FYPDB {
    private static FYPDB instance = null;
    private List<Student> students;
    private List<Supervisor> supervisors;
    private List<Project> projects;
    private List<FYPCoordinator> fypCoordinator;

    private FYPDB() {
        students = SystemInitializer.initializeStudentList("students.txt");
        projects = SystemInitializer.initializeProjectList("projects2.txt");
        fypCoordinator = SystemInitializer.initializeFYPCoordinatorList("FYPCoordinator.txt");
        supervisors = SystemInitializer.initializeFacultyList("supervisor.txt");
    }

    public static FYPDB getInstance() {
        if (instance == null) {
            instance = new FYPDB();
        }
        return instance;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Supervisor> getSupervisors() {
        return supervisors;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void addProject(Project project){
        this.projects.add(project);
    }

    public List<FYPCoordinator> getFypCoordinator() {
        return fypCoordinator;
    }

    // Add other methods for modifying the lists as required
}
