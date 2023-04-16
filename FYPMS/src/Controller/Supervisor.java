package Controller;

import java.util.ArrayList;
import java.util.List;

import Entity.Project;
import Entity.Request;
import Entity.Request_ChangeSupervisor;
import Entity.User;
import Enum.*;
import Database.*;


/**
 * The Supervisor class represents a supervisor user in the FYP System.
 * A Supervisor can create projects, view their assigned projects, request student transfers,
 * handle incoming and outgoing requests, and manage project availability.
 * @author Ian Sim, Ding Jiang
 * @version 1.0.0 Apr 16, 2023
 */
public class Supervisor extends User {
    private List<Project> projects;
    private int projCount;
    private final int MAXPROJECTS = 2;

    /**
     * Constructs a Supervisor object with the given name, email, and user ID.
     * Initializes the projects list and projCount.
     *
     * @param name the name of the supervisor
     * @param email the email address of the supervisor
     * @param userID the user ID of the supervisor
     */
    public Supervisor(String name, String email, String userID) {
        super(name, email, userID);
        this.projects = new ArrayList<>();
        this.projCount = 0;
    }

    /**
     * Creates a project with the given title and adds it to the FYP database.
     * If the supervisor has more than two projects assigned, makes their projects unavailable.
     *
     * @param title the title of the project to be created
     */
    public void createProject(String title) {
        List<Project> projectList = FYPDB.getInstance().getProjects();
        int projectID = projectList.size() + 1;
        Supervisor supervisor = this;
        Project project = new Project(projectID, supervisor, title);
        projectList.add(project);
        if (this.projCount > 2) {
            this.makeProjectUnavailable();
        }

    }

    /**
     * Returns the list of projects assigned to the supervisor.
     *
     * @return the list of projects assigned to the supervisor
     */
    public List<Project> getProjects() {
        return projects;
    }

    /**
     * Prints the details of the projects assigned to the supervisor.
     */
    public void viewProjects(){
        List<Project> projects = FYPDB.getInstance().getProjects();
        for (Project project : projects){
            if(project.getSupervisor().getID().equals(this.getID())){
                System.out.println(project.toString());
            }
        }
    }

    /**
     * Sends a request to the FYPCoordinator to transfer a student to a replacement supervisor.
     *
     * @param projectID the ID of the project for which a student transfer is requested
     * @param supervisorID the ID of the replacement supervisor
     */
    public void requestStudentTransfer(int projectID, String supervisorID) {
        // Implement the logic to send a request to the FYP coordinator to transfer a student to a replacement supervisor.
        FYPCoordinator fypCoordinator = FYPDB.getInstance().getFypCoordinator().get(0);
        Request request = new Request_ChangeSupervisor(this.getID(), fypCoordinator.getID(), RequestType.CHANGE_SUPERVISOR, projectID, supervisorID);
        fypCoordinator.addIncomingRequest(request);
        this.addOutgoingRequest(request);

        fypCoordinator.addRequest(request);
    }

    /**
     * Prints the details of pending title change requests received by the supervisor.
     */
    public void printPendingRequests() {
        List<Request> requests = this.getIncomingRequest();
        for (int i = 0; i < requests.size(); i++) {
            if (requests.get(i).getReqStatus() == RequestStatus.PENDING && requests.get(i).getReqType() == RequestType.CHANGE_TITLE) {
                System.out.println(requests.get(i).toString());
            }
        }

    }
    /**
     * Returns whether the supervisor has any pending title change requests.
     * @return true if the supervisor has pending title change requests, false otherwise
     */
    public boolean hasPendingRequests() {
        List<Request> requests = this.getIncomingRequest();
        for (int i = 0; i < requests.size(); i++) {
            if (requests.get(i).getReqStatus() == RequestStatus.PENDING && requests.get(i).getReqType() == RequestType.CHANGE_TITLE) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prints a list of requests handled by the supervisor, excluding requests for capacity changes by the FYPCoordinator.
     */
    public void PrintAllRequest() {
        // Implement the logic to return a list of requests handled by the supervisor. (Excluding that of capacity of FYPCoordinator)
        System.out.println("ALL INCOMING REQUESTS:\n");
        List<Request> incomingRequests = this.getIncomingRequest();
        List<Request> filteredRequests = new ArrayList<>();
        // Check request type, keeping only those of type change title
        for(Request request : incomingRequests){
            if(request.getReqType() == RequestType.CHANGE_TITLE){
                filteredRequests.add(request);
            }
        }
        for (int i = 0; i < filteredRequests.size(); i++) {
            System.out.println(filteredRequests.get(i).toString());
        }
        System.out.println("ALL OUTGOING REQUESTS:\n");
        List<Request> outgoingRequests = this.getOutgoingRequest();
        for (int i =0; i < outgoingRequests.size(); i++) {
            System.out.println(outgoingRequests.get(i).toString());
        }

    }

    /**
     * Adds 1 to the supervisor's project count.
     */
    public void addProjCount(){
        this.projCount++;
    }

    /**
     * Subtracts 1 from the supervisor's project count.
     */
    public void subProjCount(){
        this.projCount--;
    }


    /**
     * Makes all projects assigned to the supervisor unavailable, if the supervisor has at least 2 projects assigned.
     */
    public void makeProjectUnavailable(){
        if(this.projCount < 2){
            return;
        }

        FYPDB fypdb = FYPDB.getInstance();
        List<Project> projects = fypdb.getProjectBySupID(this.getID());
        for(Project project: projects){
            if (project.getStatus() == ProjectStatus.AVAILABLE){
                project.setStatus(ProjectStatus.UNAVAILABLE);
            }
        }
    }

    /**
     * Makes all projects assigned to the supervisor available, if the supervisor has only 1 project assigned.
     */
    public void makeProjectAvailable(){
        if(this.projCount > 1){
            return;
        }

        FYPDB fypdb = FYPDB.getInstance();
        List<Project> projects = fypdb.getProjectBySupID(this.getID());
        for(Project project: projects){
            if (project.getStatus() == ProjectStatus.UNAVAILABLE){
                project.setStatus(ProjectStatus.AVAILABLE);
            }
        }
    }
}
