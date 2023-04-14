import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Scanner;

public interface ProjectHandler {
    /**
     * Scanner object created to allow for user inputs
     */
    public Scanner sc = new Scanner(System.in);

    default void addProject(Supervisor supervisor){
        LinkedHashMap<String, Project> projects = ProjectDB.getInstance().getProjectLinkedHashMap();
        int projectID = projects.size() + 1;
        String supervisorName = supervisor.getName();
        String supervisorID = supervisor.getSupervisorID();
        String supervisorEmail = supervisor.getEmail();
        System.out.println("Enter Project Title");
        String projectTitle = sc.nextLine();
        ProjectStatus status = ProjectStatus.AVAILABLE;
        Project project = new Project(projectID, supervisorName, supervisorID, supervisorEmail, projectTitle);
        int newID = project.getProjectID();
        project.setID(newID);
        projects.put(Integer.toString(projectID), project);
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


    // Use in FYP Coordinator class instead. TEST addProjCount
    default boolean allocateProject(int projectID) {
        Project project = ProjectDB.getInstance().getProject(projectID);
        Supervisor supervisor = SupervisorDB.getInstance().getSupervisor(project.getSupervisorID());

        if (project.getStatus() == ProjectStatus.RESERVED) {
            project.setStatus(ProjectStatus.ALLOCATED);
            supervisor.addProjCount();
            fixAvailability(supervisor);
            return true;
        }
        return false;
    }

    default boolean deregisterProject(int projectID) {
        Project project = ProjectDB.getInstance().getProject(projectID);
        Supervisor supervisor = SupervisorDB.getInstance().getSupervisor(project.getSupervisorID());
        if (project.getStatus() == ProjectStatus.ALLOCATED) {
            project.setStudentID("");
            supervisor.subProjCount();
            fixAvailability(supervisor);
        }
        project.setStatus(ProjectStatus.AVAILABLE);
        return true;
    }

//    default boolean studentReserveProject(int projectID, String studentID) {
//        // check if project is available
//        Project project = ProjectDB.getInstance().getProject(projectID);
//        if (project.getStatus() == ProjectStatus.AVAILABLE) {
//            // reserve the project for the student
//            boolean reserved = reserveProject(projectID, studentID);
//            if (reserved) {
//                // remove the project from available project list
//                LinkedHashMap<String, Project> projects = ProjectDB.getInstance().getProjectLinkedHashMap();
//
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

    default boolean rejectProject(int projectID, String studentID) {
        // check if project is reserved by the student
        Project project = ProjectDB.getInstance().getProject(projectID);
        Student students = StudentDB.getInstance().getStudent(studentID);
        if (project.getStatus() == ProjectStatus.RESERVED && project.getStudentID() == students.getStudentID()) {
            // set the project status back to available
            project.setStatus(ProjectStatus.AVAILABLE);
            return true;
        }
        return false;
    }

   // check for >2 projects
   default void fixAvailability(Supervisor supervisor){
       LinkedHashMap<String, Project> projects = ProjectDB.getInstance().getProjectLinkedHashMap();
       if(supervisor.getProjCount() > 1){
           for (Project project : projects.values()) {
               if (project.getSupervisorID() == supervisor.getSupervisorID() && project.getStatus() == ProjectStatus.AVAILABLE) {
                   project.setStatus(ProjectStatus.UNAVAILABLE);
               }
           }
       }
       else{
           for (Project project: projects.values()){
               if (project.getSupervisorID() == supervisor.getSupervisorID() && project.getStatus() == ProjectStatus.UNAVAILABLE) {
                   project.setStatus(ProjectStatus.AVAILABLE);
               }
           }
       }
   }

   default void modifyProjTitle(Supervisor supervisor){
       LinkedHashMap<String, Project> projects = ProjectDB.getInstance().getProjectLinkedHashMap();
       ArrayList<String> tempProjID = new ArrayList<>();
       for (Project project : projects.values()) {
//           System.out.println(project.getSupervisorID());
//           System.out.println(supervisor.getSupervisorID());
           if (project.getSupervisorID().equals(supervisor.getSupervisorID())) {
               // Print all projects of that supervisorID
               System.out.print(project.toString());
               tempProjID.add(Integer.toString(project.getProjectID()));
           }
       }
       try{
           System.out.println("Enter the Project ID to modify: ");
           String projectID = sc.nextLine();
           while(!tempProjID.contains(projectID)){
                System.out.println("You have entered an invalid Project ID. Enter again:");
                projectID = sc.nextLine();
           }

           Project project = projects.get(projectID);
           System.out.println("Enter the title you want to change it to: ");
           String newTitle = sc.nextLine();
           project.setProjectTitle(newTitle);
       }
       catch(Exception e){
           System.out.println("Invalid Project ID");
       }

   }

    
}
