package Database;
import java.util.ArrayList;
import java.util.List;
import Controller.Student;
import Controller.Supervisor;
import Controller.FYPCoordinator;
import Entity.Project;
/**
 * The FYPDB class represents the database of the FYP system. It contains lists of students, supervisors, projects and FYP coordinators.
 * @author Ian Sim
 * @version 1.0.0 Apr 16, 2023
 */
public class FYPDB {
    /**
     * The instance variable is used to implement the singleton design pattern.
     */
    private static FYPDB instance = null;
    /**
     * The list of students in the system.
     */
    private List<Student> students;
    /**
     * The list of supervisors in the system.
     */
    private List<Supervisor> supervisors;
    /**
     * The list of projects in the system.
     */
    private List<Project> projects;
    /**
     * The list of FYP coordinators in the system.
     */
    private List<FYPCoordinator> fypCoordinator;
    /**
     * Constructs a new FYPDB object.
     */
    private FYPDB() {
        students = SystemInitializer.initializeStudentList("students.txt");
        supervisors = SystemInitializer.initializeFacultyList("supervisor.txt");
        projects = SystemInitializer.initializeProjectList("projects.txt");
        fypCoordinator = SystemInitializer.initializeFYPCoordinatorList("FYPCoordinator.txt");
    }

    /**
     * Returns the singleton instance of the FYPDB class.
     *
     * @return the singleton instance of the FYPDB class.
     */
    public static FYPDB getInstance() {

        if (instance == null) {
            instance = new FYPDB();
        }
        return instance;
    }

    /**
     * Returns the list of students in the system.
     *
     * @return the list of students in the system.
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Returns the list of supervisors in the system.
     *
     * @return the list of supervisors in the system.
     */
    public List<Supervisor> getSupervisors() {
        return supervisors;
    }

    /**
     * Returns the list of projects in the system.
     *
     * @return the list of projects in the system.
     */
    public List<Project> getProjects() {
        return projects;
    }

    /**
     * Adds a project to the list of projects in the system.
     *
     * @param project the project to add.
     */
    public void addProject(Project project){
        this.projects.add(project);
    }

    /**
     * Returns the list of FYP coordinators in the system.
     *
     * @return the list of FYP coordinators in the system.
     */
    public List<FYPCoordinator> getFypCoordinator() {
        return fypCoordinator;
    }

    // Add other methods for modifying the lists as required

    /**
     * Returns the student object with the specified student ID.
     *
     * @param studentID the ID of the student to return.
     * @return the student object with the specified student ID, or null if the student is not found.
     */
    public Student getStudent(String studentID) {
        for (Student student: this.students){
            if (student.getID().equals(studentID)){
                return student;
            }
        }
        return null;
    }

    /**
     * Returns the supervisor object with the specified supervisor ID.
     *
     * @param supervisorID the ID of the supervisor to return.
     * @return the supervisor object with the specified supervisor ID, or null if the supervisor is not found.
     */
    public Supervisor getSupervisor(String supervisorID){
        for (Supervisor supervisor: this.supervisors){
            if(supervisor.getID().equals(supervisorID)){
                return supervisor;
            }
        }
        return null;
    }

    /**
     * Returns the project with the specified ID if it exists in the database, or null otherwise.
     *
     * @param projectID the unique ID of the project
     * @return the project with the specified ID if it exists in the database, or null otherwise.
     */
    public Project getProjectByID(int projectID){
        for (Project project: this.projects){
            if(project.getProjectID() == projectID){
                return project;
            }
        }
        return null;
    }

    /**
     * Returns a list of projects assigned to the supervisor with the specified ID, or an empty list if there are no
     * projects assigned to that supervisor.
     *
     * @param supervisorID the unique ID of the supervisor
     * @return a list of projects assigned to the supervisor with the specified ID, or an empty list if there are no
     *         projects assigned to that supervisor.
     */
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
