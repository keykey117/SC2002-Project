import java.util.ArrayList;
import java.util.List;

public class ProjectManagementSystem {
    private List<Project> projects;

    public ProjectManagementSystem() {
        projects = new ArrayList<Project>();
    }

    // Add a project to the system
    public void addProject(Project project) {
        projects.add(project);
    }

    // Get all projects in the system
    public List<Project> getProjects() {
        return projects;
    }

    // Get all available projects in the system
    public List<Project> getAvailableProjects() {
        List<Project> availableProjects = new ArrayList<Project>();
        for (Project project : projects) {
            if (project.getStatus() == ProjectStatus.AVAILABLE) {
                availableProjects.add(project);
            }
        }
        return availableProjects;
    }

    // Reserve a project for a student
    public boolean reserveProject(Project project, Student student) {
        if (project.getStatus() == ProjectStatus.AVAILABLE) {
            project.setStatus(ProjectStatus.RESERVED);
            project.setStudentID(student.getStudentID());
            project.setStudentName(student.getStudentName());
            project.setStudentEmail(student.getStudentEmail());
            return true;
        }
        return false;
    }

    // Allocate a project to a student
    public boolean allocateProject(Project project) {
        if (project.getStatus() == ProjectStatus.RESERVED) {
            project.setStatus(ProjectStatus.ALLOCATED);
            return true;
        }
        return false;
    }

    // Deregister a project
    public boolean deregisterProject(Project project) {
        if (project.getStatus() == ProjectStatus.ALLOCATED) {
            project.setStudentID(-1);
        }
        project.setStatus(ProjectStatus.AVAILABLE);
        return true;
    }

    // Student reserves a project
    public boolean studentReserveProject(Project project, Student student) {
        // check if project is available
        if (project.getStatus() == ProjectStatus.AVAILABLE) {
            // reserve the project for the student
            boolean reserved = reserveProject(project, student);
            if (reserved) {
                // remove the project from available project list
                projects.remove(project);
                return true;
            }
        }
        return false;
    }

    // Student allocates a project
    public boolean studentAllocateProject(Project project, Student student) {
        // check if project is reserved by the student
        if (project.getStatus() == ProjectStatus.RESERVED && project.getStudentID() == student.getStudentID()) {
            // allocate the project to the student
            boolean allocated = allocateProject(project);
            if (allocated) {
                return true;
            }
        }
        return false;
    }

    // Student rejects a project
    public boolean studentRejectProject(Project project, Student student) {
        // check if project is reserved by the student
        if (project.getStatus() == ProjectStatus.RESERVED && project.getStudentID() == student.getStudentID()) {
            // set the project status back to available
            project.setStatus(ProjectStatus.AVAILABLE);
            return true;
        }
        return false;
    }
}
