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
}
