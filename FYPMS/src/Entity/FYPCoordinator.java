package Entity;

import java.util.*;
import Enum.*;
import Database.*;
public class FYPCoordinator extends Supervisor {
    private List<Request> allRequest;
    Scanner sc = new Scanner(System.in);
    public FYPCoordinator(String name, String email, String userID) {
        super(name, email, userID);
        this.allRequest = new ArrayList<>();
    }
    public void changeProjectSupervisor(Project project, Supervisor newSupervisor) {
        // Implement logic to change the supervisor of a project upon request
    }

    public void allocateProjectToStudent(Project curProject, Student student) {
        // Implement logic to allocate a project to a student upon request
        int projectID = curProject.getProjectID();
        List<Project> projectList = FYPDB.getInstance().getProjects();
        for(Project project : projectList){
            if(project.getProjectID() == projectID){
                project.setStudent(student);
                project.setStatus(ProjectStatus.ALLOCATED);
            }
        }
    }

    public void deregisterStudentFromProject(Student student, Project project) {
        // Implement logic to deregister a student from a project upon request
    }


    public void viewAllProjects() {
        List<Project> projects = FYPDB.getInstance().getProjects();
        for (Project project : projects){
            System.out.println(project.toString());
        }
        return;
    }

    public List<Project> generateProjectDetailsReport(Map<String, String> filters) {
        // Implement logic to generate a project details report based on the provided filters
        return null;
    }

    public void generateProjectDetailsReport(){

        System.out.println("\n--------------Filters Panel--------------");
        System.out.println("Option Available: (1-6):");
        System.out.println("(1) Project ID");
        System.out.println("(2) Supervisor ID");
        System.out.println("(3) Student ID");
        System.out.println("(4) Project Status");

        int filterType = sc.nextInt();
        sc.nextLine();
        List<Project> projectList = FYPDB.getInstance().getProjects();
        int flag = 0;
        switch(filterType){
            // Project ID
            case 1:
                System.out.println("Enter the project ID: ");
                int projectID;
                try {
                    projectID = sc.nextInt();
                    sc.nextLine();
                } catch (InputMismatchException err) {
                    System.out.println("Error: Please input a valid number for project ID.\n");
                    sc.nextLine();
                    break;
                }
                flag = 0;
                for (Project project : projectList) {
                    if (project.getProjectID() == projectID) {
                        System.out.println(project.toString());
                        flag = 1;
                    }
                }
                if (flag == 0) {
                    System.out.println("Invalid projectID");
                }
                break;
            case 2:
                System.out.println("Enter the supervisor ID: ");
                String supervisorID = sc.nextLine();
                for(Project project : projectList){
                    if(project.getSupervisor().getID().equals(supervisorID)){
                        System.out.println(project.toString());
                        flag = 1;

                    }
                }
                if (flag == 0){
                    System.out.println("Invalid supervisorID");
                }
                break;
            case 3:
                try{
                    System.out.println("Enter the student ID: ");
                    String studentID = sc.nextLine();
                    for(Project project : projectList){
                        if(project.getStudent().getID().equals(studentID)){
                            System.out.println(project.toString());
                            flag = 1;
                        }
                    }
                }
                catch(Exception e){
                    System.out.println("Error occurred: " + e.getMessage());
                }
                if (flag == 0){
                    System.out.println("Invalid projectID");
                }
                break;
            case 4:
                System.out.println("Enter the Project status:");
                System.out.println("(1) AVAILABLE");
                System.out.println("(2) RESERVED");
                System.out.println("(3) ALLOCATED");
                System.out.println("(4) UNAVAILABLE");
                int statusType = sc.nextInt();

                if (statusType < 1 || statusType > 4){
                    System.out.println("You have entered an invalid Project Status");
                }
                else {
                    ProjectStatus status;
                    sc.nextLine();
                    switch (statusType) {
                        case 2:
                            status = ProjectStatus.RESERVED;
                            break;
                        case 3:
                            status = ProjectStatus.ALLOCATED;
                            break;
                        case 4:
                            status = ProjectStatus.UNAVAILABLE;
                            break;
                        default:
                            status = ProjectStatus.AVAILABLE;
                    }
                    for (Project project : projectList) {
                        if (project.getStatus() == status) {
                            System.out.println(project.toString());
                            flag = 1;
                        }
                    }
                }
                if (flag == 0){
                    System.out.println("No Projects with selected status");
                }
                break;
        }
    }

//    public void printPendingRequests() {
//        // Implement logic to return a list of pending requests sent by supervisors and students
//        List<Request> requests = this.GetIncomingRequest();
//        for (int i = 0; i < requests.size(); i++) {
//            if (requests.get(i).getReqStatus() == RequestStatus.PENDING) {
//                System.out.println(requests.get(i).toString());
//            }
//        }
//
//    }

    public void approve(Request request) {
        // Implement logic to approve the request sent by a supervisor or a student
        request.approve();
        //implement in Request
    }

    public void rejectRequest(Request request) {
        // Implement logic to reject the request sent by a supervisor or a student
        request.reject();
    }

    public void PrintAllRequest() {
        // Implement logic to return a list of all requests handled by the FYP coordinator
        System.out.println("ALL REQUESTS:\n");
        for (int i = 0; i < this.allRequest.size(); i++) {
            System.out.println(this.allRequest.get(i).toString());
        }
    }

    public void addRequest(Request req) {
        this.allRequest.add(req);
    }
}

