import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public ProjectManagementSystem() {
        projects = new ArrayList<Project>();
        lastProjectId = 0;
        loadProjectsFromExcel();
        }

// Load projects from rollover project file
    private void loadProjectsFromExcel() {
        File rolloverProjectFile = new File("rollover project.xlsx");
        try {
        Scanner scanner = new Scanner(rolloverProjectFile);
        // Skip the first line which is the header
        if (scanner.hasNextLine()) {
        scanner.nextLine();
        }
        while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] parts = line.split(",");
        String supervisor = parts[0];
        String title = parts[1];
        Project project = new Project(++lastProjectId, supervisor, title);
        addProject(project);
        }
        scanner.close();
        } catch (IOException e) {
        System.out.println("Failed to load rollover project file: " + e.getMessage());
        }
        }

// Add a project to the system
    public void addProject(Project project) {
        project.setId(++lastProjectId);
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
