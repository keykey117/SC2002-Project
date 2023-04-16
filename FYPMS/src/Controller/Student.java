package Controller;

import java.util.List;

import Controller.FYPCoordinator;
import Controller.Supervisor;
import Entity.*;
import Enum.*;
import Database.*;
public class Student extends User {
    private Project project;
    private boolean deregistered;
    public Student(String name, String email, String userID) {
        super(name, email, userID);

        this.project = null;
        this.deregistered = false;
    }

    public Project getProject(){
        return this.project;
    }
    public void setProject(Project project){
        this.project = project;
    }
    public void setDeregistered(){this.deregistered = true;}
    public boolean getDeregistered(){return this.deregistered;}
    public void viewAvailableProjects() {
        // Implement logic to return a list of available projects
        List<Project> projects = FYPDB.getInstance().getProjects();

        if(project == null && this.deregistered == false){
            for (Project project: projects) {
                if (project.getStatus() == ProjectStatus.AVAILABLE) {
                    System.out.println(project.toString());
                }
            }
        }
        else if(this.deregistered == true){
            System.out.println("You are not allowed to make selection again as you have deregistered your FYP.");
        }
        else{
            System.out.println("You have already registered for a project");
        }
    }

    public void requestProjectAllocation(Project project) {
        // Implement logic to send request to the FYP coordinator to allocate the project
        FYPCoordinator fypCoordinator = FYPDB.getInstance().getFypCoordinator().get(0);
        Request_RegisterFYP requestRegisterFYP = new Request_RegisterFYP(this.getID(), fypCoordinator.getID(),RequestType.REGISTER_FYP,project.getProjectID());
        this.addOutgoingRequest(requestRegisterFYP);
        fypCoordinator.addIncomingRequest(requestRegisterFYP);
        project.setStatus(ProjectStatus.RESERVED);

        fypCoordinator.addRequest(requestRegisterFYP);
    }

    public void requestTitleChange(String newTitle) {
        // Implement logic to send request to the supervisor to change the project title
//        System.out.println(this.getProject().getSupervisor().getID());
        Supervisor supervisor = FYPDB.getInstance().getSupervisor(this.getProject().getSupervisor().getID());
        FYPCoordinator fypCoordinator = FYPDB.getInstance().getFypCoordinator().get(0);
        // Supervisor supervisor = this.getProject().getSupervisor();

        Request_ChangeTitle requestChangeTitle = new Request_ChangeTitle(this.getID(), supervisor.getID(), RequestType.CHANGE_TITLE,this.getProject().getProjectID(), newTitle);
        this.addOutgoingRequest(requestChangeTitle);
        supervisor.addIncomingRequest(requestChangeTitle);

        // give FYPCoordinator this request to view
        fypCoordinator.addRequest(requestChangeTitle);
    }

    public void requestDeregistration() {
        // Implement logic to send request to the FYP coordinator to deregister from the allocated project
        FYPCoordinator fypCoordinator = FYPDB.getInstance().getFypCoordinator().get(0);
        Request_DeregisterFYP requestDeregisterFYP = new Request_DeregisterFYP(this.getID(), fypCoordinator.getID(), RequestType.DEREGISTER_FYP,project.getProjectID());
        this.addOutgoingRequest(requestDeregisterFYP);
        fypCoordinator.addIncomingRequest(requestDeregisterFYP);

        fypCoordinator.addRequest(requestDeregisterFYP);
    }

    public List<Request> viewRequestHistory() {
        // Implement logic to return a list of requests made by the student

        return null;
    }

    @Override
    public void PrintAllRequest() {
        // Implement logic to return a list of all requests outgoing by Student
        System.out.println("ALL REQUESTS\n");
        List<Request> outgoingRequest = this.GetOutgoingRequest();
        for (int i = 0; i < outgoingRequest.size(); i++) {
            System.out.println(outgoingRequest.get(i).toString());
        }
    }
}
