package Controller;

import java.util.*;

import Entity.Project;
import Entity.Request;
import Enum.*;
import Database.*;
/**
 * This class represents a FYP Coordinator who is responsible for handling various tasks related to FYP project management
 * such as changing project supervisor, allocating project to student, deregistering student from project,
 * generating project details report, and viewing all projects.
 * @author Ian Sim, Key Yew, Ding Jiang
 * @version 1.0.0 Apr 16, 2023
 */
public class FYPCoordinator extends Supervisor {
    private List<Request> allRequest;
    Scanner sc = new Scanner(System.in);

    /**
     * Constructor to create a new FYPCoordinator object.
     *
     * @param name The name of the FYP Coordinator
     * @param email The email of the FYP Coordinator
     * @param userID The user ID of the FYP Coordinator
     */
    public FYPCoordinator(String name, String email, String userID) {
        super(name, email, userID);
        this.allRequest = new ArrayList<>();
    }


    /**
     * Displays all the available FYP projects.
     */
    public void viewAllProjects() {
        List<Project> projects = FYPDB.getInstance().getProjects();
        for (Project project : projects){
            System.out.println(project.toString());
        }
        return;
    }

    /**
     * Generates a project details report based on the user input for filters.
     */
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

    /**
     * Prints pending requests for supervisor role where request type is 'CHANGE_TITLE'
     */
    public void printSupRolePendingRequests(){
        List<Request> requests = this.getIncomingRequest();
        List<Request> filteredRequests = new ArrayList<>();
        int flag = 0;
        // Check request type, keeping only those of type change title
        for(Request request : requests){
            if(request.getReqStatus() == RequestStatus.PENDING && request.getReqType() == RequestType.CHANGE_TITLE){
                filteredRequests.add(request);
                flag = 1;
            }
        }
        for (int i = 0; i < filteredRequests.size(); i++) {
            System.out.println(filteredRequests.get(i).toString());
        }
        if(flag == 0){
            System.out.println("No pending requests");
        }
    }

    /**
     * Checks if supervisor role has any pending requests where request type is 'CHANGE_TITLE'
     * @return true if there are any pending requests, false otherwise
     */
    public boolean hasSupRolePendingRequests() {
        List<Request> requests = this.getIncomingRequest();
        for (int i = 0; i < requests.size(); i++) {
            if (requests.get(i).getReqStatus() == RequestStatus.PENDING && requests.get(i).getReqType() == RequestType.CHANGE_TITLE) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prints pending requests where request type is not 'CHANGE_TITLE'
     */
    public void printPendingRequests() {
        // Implement logic to return a list of pending requests sent by supervisors and students
        List<Request> requests = this.getIncomingRequest();
        List<Request> filteredRequests = new ArrayList<>();
        int flag = 0;
        // Check request type, keeping only those of type change title
        for(Request request : requests){
            if(request.getReqStatus() == RequestStatus.PENDING && request.getReqType() != RequestType.CHANGE_TITLE){
                filteredRequests.add(request);
                flag = 1;
            }
        }
        for (int i = 0; i < filteredRequests.size(); i++) {
            System.out.println(filteredRequests.get(i).toString());
        }
        if(flag == 0){
            System.out.println("No pending requests");
        }
    }

    /**
     * Checks if there are any pending requests where request type is not 'CHANGE_TITLE'
     * @return true if there are any pending requests, false otherwise
     */
    public boolean hasPendingRequests() {
        List<Request> requests = this.getIncomingRequest();
        for (int i = 0; i < requests.size(); i++) {
            if (requests.get(i).getReqStatus() == RequestStatus.PENDING && requests.get(i).getReqType() != RequestType.CHANGE_TITLE) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prints all requests handled by FYP coordinator, excluding capacity of supervisor
     */
    public void printAllRequest() {
        // Implement logic to return a list of all requests handled by the FYP coordinator (Excluding that of capacity of supervisor)
        System.out.println("ALL REQUESTS:\n");
        List<Request> requests = this.allRequest;
        List<Request> filteredRequests = new ArrayList<>();
        for(Request request : filteredRequests){
            if(request.getReqType() != RequestType.CHANGE_TITLE){
                filteredRequests.add(request);
            }
        }
        for (int i = 0; i < filteredRequests.size(); i++) {
            System.out.println(filteredRequests.get(i).toString());
        }
    }

    /**
     * Adds a new request to the list of all requests
     * @param req the request to add
     */
    public void addRequest(Request req) {
        this.allRequest.add(req);
    }

    /**
     * Prints the details of all requests of a specified type.
     *
     * @param requestType the type of request to be displayed.
     */
    public void viewRequestOfType(RequestType requestType){
        List<Request> requests = this.getIncomingRequest();
        for(Request request : requests){
            if(request.getReqType() == requestType){
                System.out.println(request.toString());
            }
        }
    }

    /**
     * Returns a list of pending requests of a specified type.
     *
     * @param requestType the type of request to be returned.
     * @return a list of pending requests of the specified type.
     */
    public List<Request> getPendingRequestOfType(RequestType requestType){
        List<Request> requestList = new ArrayList<>();
        List<Request> requests = this.getIncomingRequest();
        for(Request request : requests){
            if(request.getReqType() == requestType && request.getReqStatus() == RequestStatus.PENDING){
                requestList.add(request);
            }
        }
        return requestList;
    }
}

