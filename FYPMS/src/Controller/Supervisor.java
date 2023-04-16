package Controller;

import java.util.ArrayList;
import java.util.List;

import Entity.Project;
import Entity.Request;
import Entity.Request_ChangeSupervisor;
import Entity.User;
import Enum.*;
import Database.*;



public class Supervisor extends User {
    private List<Project> projects;
    private int projCount;
    private final int MAXPROJECTS = 2;

    public Supervisor(String name, String email, String userID) {
        super(name, email, userID);
        this.projects = new ArrayList<>();
        this.projCount = 0;
    }

    public void createProject(String title) {
        List<Project> projectList = FYPDB.getInstance().getProjects();
        int projectID = projectList.size() + 1;
        Supervisor supervisor = this;
        Project project = new Project(projectID, supervisor, title);
        projectList.add(project);

    }

    public List<Project> getProjects() {
        return projects;
    }

    public void viewProjects(){
        List<Project> projects = FYPDB.getInstance().getProjects();
        for (Project project : projects){
            if(project.getSupervisor().getID().equals(this.getID())){
                System.out.println(project.toString());
            }
        }
    }

    public void modifyTitle(Project project, String newTitle) {
        project.setTitle(newTitle);
    }


    public void requestStudentTransfer(int projectID, String supervisorID) {
        // Implement the logic to send a request to the FYP coordinator to transfer a student to a replacement supervisor.
        FYPCoordinator fypCoordinator = FYPDB.getInstance().getFypCoordinator().get(0);
        Request request = new Request_ChangeSupervisor(this.getID(), fypCoordinator.getID(), RequestType.CHANGE_SUPERVISOR, projectID, supervisorID);
        fypCoordinator.addIncomingRequest(request);
        this.addOutgoingRequest(request);

        fypCoordinator.addRequest(request);
    }

    public void PrintPendingRequestsFromStudents() {
        // Implement the logic to return a list of pending requests from students.
        List<Request> requests = this.getIncomingRequest();
        for (int i = 0; i < requests.size(); i++) {
            if (requests.get(i).getReqStatus() == RequestStatus.PENDING) {
                requests.get(i).toString();
            }
        }
    }

    public void approveRequest_TitleChange(Request request) {
        // Implement the logic to approve the request made by a student.
        request.approve();
        //TODO find Project object and change its title.
    }

    public void rejectRequest(Request request) {
        // Implement the logic to reject the request made by a student.
        request.reject();
    }

    public void PrintAllRequest() {
        // Implement the logic to return a list of requests handled by the supervisor.
        System.out.println("ALL INCOMING REQUESTS:\n");
        List<Request> incomingRequests = this.getIncomingRequest();
        for (int i = 0; i < incomingRequests.size(); i++) {
            System.out.println(incomingRequests.get(i).toString());
        }
        System.out.println("ALL OUTGOING REQUESTS:\n");
        List<Request> outgoingRequests = this.getOutgoingRequest();
        for (int i =0; i < outgoingRequests.size(); i++) {
            System.out.println(outgoingRequests.get(i).toString());
        }

    }


    // Getter and setter methods for the attributes
    public void addAllProjects(){

    }

    public void addProjCount(){
        this.projCount++;
    }

    public void subProjCount(){
        this.projCount--;
    }

    public int getProjCount(){
        return this.projCount;
    }

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
