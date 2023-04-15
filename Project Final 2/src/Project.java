public class Project {
    private int projectID;
    private Supervisor supervisor;
    private Student student;
    private String title;
    private ProjectStatus status;

    public Project(int projectID, Supervisor supervisor, String title) {
        this.projectID = projectID;
        this.supervisor = supervisor;
        this.student = null;
        this.title = title;
        this.status = ProjectStatus.AVAILABLE;
    }
    public int getProjectID() {return this.projectID;}
    public Supervisor getSupervisor() {return this.supervisor;}
    public void setSupervisor(Supervisor replacementSupervisor) {this.supervisor = replacementSupervisor;}
    public Student getStudent() {return this.student;}
    public void setStudent(Student assignedStudent) {this.student = assignedStudent;}
    public String getTitle() {return this.title;}
    public void setTitle(String newTitle) {this.title = newTitle;}
    public ProjectStatus getStatus() {
        return this.status;
    }
    public void setStatus(ProjectStatus newStatus) {
        this.status = newStatus;
    }

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
        }
        return sb.toString();
    }



}
