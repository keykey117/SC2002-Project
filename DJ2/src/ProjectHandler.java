import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public interface ProjectHandler {
    /**
     * Scanner object created to allow for user inputs
     */
    public Scanner sc = new Scanner(System.in);

    default void addProject(Project project){
        int newID = project.getProjectID() + 1;
        project.setID(newID);
    }

    default List<Project> getProjects(){
        LinkedHashMap<String, Project> projects = ProjectDB.getInstance().getProjectLinkedHashMap();
        List<Project> projectList = new ArrayList<>();
        for (Project project: projects.values()) {
            projectList.add(project);
        }
        return projectList;
    }

    default List<Project> getAvailableProjects(){
        LinkedHashMap<String, Project> projects = ProjectDB.getInstance().getProjectLinkedHashMap();
        List<Project> projectList = new ArrayList<>();
        for (Project project: projects.values()) {
            if (project.getStatus() == ProjectStatus.AVAILABLE) {
                projectList.add(project);
            }
        }
        return projectList;
    }

    default void printAvailProj() {
        LinkedHashMap<String, Project> projects = ProjectDB.getInstance().getProjectLinkedHashMap();
        for (Project project: projects.values()) {
            if (project.getStatus() == ProjectStatus.AVAILABLE) {
                System.out.println(project.toString());
            }
        }
    }

    default boolean reserveProject(int projectID, String studentID){
        // Check projectID against HashMap
//        LinkedHashMap<String, Project> projects = ProjectDB.getInstance().getProjectLinkedHashMap();
//        Project project = projects.get(projectID);

        Project project = ProjectDB.getInstance().getProject(projectID);
        Student students = StudentDB.getInstance().getStudent(studentID);

        // Change Project status
        if(project.getStatus()==ProjectStatus.AVAILABLE){
            project.setStatus(ProjectStatus.RESERVED);
            project.setStudentID(students.getStudentID());
            project.setStudentName(students.getName());
            project.setStudentEmail(students.getEmail());
            return true;
        }
        return false;
    }

    default boolean allocateProject(int projectID) {
        Project project = ProjectDB.getInstance().getProject(projectID);
        if (project.getStatus() == ProjectStatus.RESERVED) {
            project.setStatus(ProjectStatus.ALLOCATED);
            return true;
        }
        return false;
    }

    default boolean deregisterProject(int projectID) {
        Project project = ProjectDB.getInstance().getProject(projectID);
        if (project.getStatus() == ProjectStatus.ALLOCATED) {
            project.setStudentID("");
        }
        project.setStatus(ProjectStatus.AVAILABLE);
        return true;
    }

//    default boolean studentReserveProject(int projectID, String studentID) {
//        Project project = ProjectDB.getInstance().getProject(projectID);
//        Student students = StudentDB.getInstance().getStudent(studentID);
//        // check if project is available
//        if (project.getStatus() == ProjectStatus.AVAILABLE) {
//            // reserve the project for the student
//            boolean reserved = reserveProject(project, students);
//            if (reserved) {
//                // remove the project from available project list
//                projects.remove(projects);
//                return true;
//            }
//        }
//        return false;
//    }

//    default boolean studentAllocateProject(Project project, Student student) {
//        // check if project is reserved by the student
//        if (project.getStatus() == ProjectStatus.RESERVED && project.getStudentID() == student.getStudentID()) {
//            // allocate the project to the student
//            boolean allocated = allocateProject(project);
//            if (allocated) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    default boolean studentRejectProject(Project project, Student student) {
//        // check if project is reserved by the student
//        if (project.getStatus() == ProjectStatus.RESERVED && project.getStudentID() == student.getStudentID()) {
//            // set the project status back to available
//            project.setStatus(ProjectStatus.AVAILABLE);
//            return true;
//        }
//        return false;
//    }


    
}
