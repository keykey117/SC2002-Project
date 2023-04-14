/**
 * Contains the individual details of each Project
 */
public class Project {
    private int projectID;
    private String supervisorName;
    private String supervisorEmail;
    private String projectTitle;
    private String studentID;
    private String studentName;
    private String studentEmail;
    private ProjectStatus status;

    public Project(int projectID, String supervisorName, String supervisorEmail, String projectTitle) {
        this.projectID = projectID;
        this.supervisorName = supervisorName;
        this.supervisorEmail = supervisorEmail;
        this.projectTitle = projectTitle;
        this.studentID = ""; // "" indicates that no student is assigned yet
        this.studentName = "";
        this.studentEmail = "";
        this.status = ProjectStatus.AVAILABLE;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setID (int projectID) {this.projectID = projectID;}

    public String getSupervisorName() {
        return supervisorName;
    }

    public String getSupervisorEmail() {
        return supervisorEmail;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public boolean isAvailable() {
        return status == ProjectStatus.AVAILABLE;
    }

    public boolean isReserved() {
        return status == ProjectStatus.RESERVED;
    }

    public boolean isAllocated() {
        return status == ProjectStatus.ALLOCATED;
    }

    public boolean isDeregistered() {
        return status == ProjectStatus.DEREGISTERED;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Project ID: ").append(projectID).append("\n");
        sb.append("Supervisor: ").append(supervisorName).append(", ").append(supervisorEmail).append("\n");
        sb.append("Title: ").append(projectTitle).append("\n");
        if (status == ProjectStatus.AVAILABLE) {
            sb.append("Status: Available\n");
        } else if (status == ProjectStatus.RESERVED) {
            sb.append("Status: Reserved\n");
            sb.append("Reserved by: ").append(studentName).append(", ").append(studentEmail).append("\n");
        } else if (status == ProjectStatus.ALLOCATED) {
            sb.append("Status: Allocated\n");
            sb.append("Allocated to: ").append(studentName).append(", ").append(studentEmail).append("\n");
        } else if (status == ProjectStatus.DEREGISTERED) {
            sb.append("Status: Deregistered\n");
        }
        return sb.toString();
    }
}


