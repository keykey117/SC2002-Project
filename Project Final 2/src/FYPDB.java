import javax.print.attribute.SupportedValuesAttribute;
import java.util.ArrayList;
import java.util.List;

public class FYPDB {
    private static FYPDB instance = null;
    private List<Student> students;
    private List<Supervisor> supervisors;
    private List<Project> projects;
    private List<FYPCoordinator> fypCoordinator;

    private FYPDB() {
        students = SystemInitializer.initializeStudentList("students.txt");
        supervisors = SystemInitializer.initializeFacultyList("supervisor.txt");
        projects = SystemInitializer.initializeProjectList("projects2.txt");
        fypCoordinator = SystemInitializer.initializeFYPCoordinatorList("FYPCoordinator.txt");
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

    public Student getStudent(String studentID) {
        for (Student student: this.students){
            if (student.getID().equals(studentID)){
                return student;
            }
        }
        return null;
    }

    public Supervisor getSupervisor(String supervisorID){
        for (Supervisor supervisor: this.supervisors){
            if(supervisor.getID().equals(supervisorID)){
                return supervisor;
            }
        }
        return null;
    }

    public Project getProjectByID(int projectID){
        for (Project project: this.projects){
            if(project.getProjectID() == projectID){
                return project;
            }
        }
        return null;
    }

    public List<Project> getProjectBySupID(String supervisorID){
        List<Project> tempList = new ArrayList<>();
        for (Project project: this.projects){
            if(project.getSupervisor().getID().equals(supervisorID)){
                tempList.add(project);
            }
        }
        return tempList;
    }
}
