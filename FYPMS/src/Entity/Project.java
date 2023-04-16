package Entity;
import Controller.Student;
import Controller.Supervisor;
import Enum.ProjectStatus;
/**
 * This class represents a project that can be assigned to a student.
 * @author Nobel
 * @version 1.0.0 Apr 16, 2023
 */
public class Project {
    private int projectID;
    private Supervisor supervisor;
    private Student student;
    private String title;
    private ProjectStatus status;

    /**
     * Creates a new project with the specified ID, supervisor, and title.
     *
     * @param projectID The ID of the project.
     * @param supervisor The supervisor of the project.
     * @param title The title of the project.
     */
    public Project(int projectID, Supervisor supervisor, String title) {
        this.projectID = projectID;
        this.supervisor = supervisor;
        this.student = null;
        this.title = title;
        this.status = ProjectStatus.AVAILABLE;
    }
    /**
     * Gets the ID of the project.
     *
     * @return The ID of the project.
     */
    public int getProjectID() {return this.projectID;}
    /**
     * Gets the supervisor of the project.
     *
     * @return The supervisor of the project.
     */
    public Supervisor getSupervisor() {return this.supervisor;}
    /**
     * Sets the supervisor of the project.
     *
     * @param replacementSupervisor The new supervisor of the project.
     */
    public void setSupervisor(Supervisor replacementSupervisor) {this.supervisor = replacementSupervisor;}
    /**
     * Gets the student assigned to the project.
     *
     * @return The student assigned to the project (or null if no student is assigned).
     */
    public Student getStudent() {return this.student;}
    /**
     * Sets the student assigned to the project.
     *
     * @param assignedStudent The new student assigned to the project (or null to remove the assigned student).
     */

    public void setStudent(Student assignedStudent) {this.student = assignedStudent;}

    /**
     * Gets the title of the project.
     *
     * @return The title of the project.
     */
    public String getTitle() {return this.title;}
    /**
     * Sets the title of the project.
     *
     * @param newTitle The new title of the project.
     */
    public void setTitle(String newTitle) {this.title = newTitle;}
    /**
     * Gets the status of the project.
     *
     * @return The status of the project.
     */
    public ProjectStatus getStatus() {
        return this.status;
    }
    /**
     * Sets the status of the project.
     *
     * @param newStatus The new status of the project.
     */
    public void setStatus(ProjectStatus newStatus) {
        this.status = newStatus;
    }

    /**
     * Returns a string representation of the project.
     *
     * @return A string representation of the project.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Project ID: ").append(projectID).append("\n");
        sb.append("Supervisor: ").append(supervisor.getName()).append(", ").append(supervisor.getEmail()).append("\n");
        sb.append("Title: ").append(title).append("\n");
        if (status == ProjectStatus.AVAILABLE) {
            sb.append("Status: Available\n");
        } else if (status == ProjectStatus.RESERVED) {
            sb.append("Status: Reserved\n");
            sb.append("Reserved by: ").append(student.getName()).append(", ").append(student.getEmail()).append("\n");
        } else if (status == ProjectStatus.ALLOCATED) {
            sb.append("Status: Allocated\n");
            sb.append("Allocated to: ").append(student.getName()).append(", ").append(student.getEmail()).append("\n");
        }else if (status == ProjectStatus.UNAVAILABLE){
            sb.append("Status: Unavailable\n");
        }
        return sb.toString();
    }



}
