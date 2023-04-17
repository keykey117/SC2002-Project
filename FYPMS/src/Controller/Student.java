package Controller;

import java.util.List;
import java.util.Objects;

import Entity.*;
import Enum.*;
import Database.*;

/**
 * The Student class represents a student user of the FYP system.
 * It extends the User class and adds additional functionality for registering and managing FYP projects.
 * @author Ian Sim, Ding Jiang
 * @version 1.0.0 Apr 16, 2023
 */
public class Student extends User {
    private Project project;
    private boolean deregistered;

    /**
     * Creates a new Student object with the given name, email, and user ID.
     * The student's project is initially set to null, and their deregistered status is set to false.
     * @param name The name of the student.
     * @param email The email address of the student.
     * @param userID The unique user ID of the student.
     */
    public Student(String name, String email, String userID) {
        super(name, email, userID);

        this.project = null;
        this.deregistered = false;
    }

    /**
     * Returns the project that this student is registered for, or null if they have not yet registered for a project.
     * @return The student's current project.
     */
    public Project getProject(){
        return this.project;
    }

    /**
     * Sets the project that this student is registered for.
     * @param project The project to register for.
     */

    public void setProject(Project project){
        this.project = project;
    }

    /**
     * Sets this student's deregistered status to true.
     */
    public void setDeregistered(){this.deregistered = true;}

    /**
     * Returns whether this student has been deregistered from the FYP system.
     * @return Whether the student has been deregistered.
     */
    public boolean getDeregistered(){return this.deregistered;}

    /**
     * Displays a list of available FYP projects that the student can register for.
     * If the student is already registered for a project or has deregistered, appropriate messages are displayed instead.
     */
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

    /**
     * Sends a request to the FYP coordinator to allocate the given project to the student.
     * The project's status is also set to RESERVED.
     * @param project The project to allocate to the student.
     */
    public void requestProjectAllocation(Project project) {
        // Implement logic to send request to the FYP coordinator to allocate the project
        FYPCoordinator fypCoordinator = FYPDB.getInstance().getFypCoordinator().get(0);
        Request_RegisterFYP requestRegisterFYP = new Request_RegisterFYP(this.getID(), fypCoordinator.getID(),RequestType.REGISTER_FYP,project.getProjectID());
        this.addOutgoingRequest(requestRegisterFYP);
        fypCoordinator.addIncomingRequest(requestRegisterFYP);
        project.setStatus(ProjectStatus.RESERVED);

        fypCoordinator.addRequest(requestRegisterFYP);
    }

    /**
     * Sends a request to the student's project supervisor to change the project's title to the given value.
     * @param newTitle The new title to set for the project.
     */
    public void requestTitleChange(String newTitle) {
        Supervisor supervisor = FYPDB.getInstance().getSupervisor(this.getProject().getSupervisor().getID());
        FYPCoordinator fypCoordinator = FYPDB.getInstance().getFypCoordinator().get(0);

        Request_ChangeTitle requestChangeTitle = new Request_ChangeTitle(this.getID(), supervisor.getID(), RequestType.CHANGE_TITLE,this.getProject().getProjectID(), newTitle);
        this.addOutgoingRequest(requestChangeTitle);
        supervisor.addIncomingRequest(requestChangeTitle);
        if (Objects.equals(supervisor.getID(), "ASFLI")) {
            fypCoordinator.addIncomingRequest(requestChangeTitle);
        }
        fypCoordinator.addRequest(requestChangeTitle);
    }

    /**
     * Sends a request to the FYP coordinator to deregister the student from the allocated project.
     * Adds the request to the student's outgoing requests and to the FYP coordinator's incoming requests.
     * Adds the request to the FYP coordinator's list of requests.
     */
    public void requestDeregistration() {
        // Implement logic to send request to the FYP coordinator to deregister from the allocated project
        FYPCoordinator fypCoordinator = FYPDB.getInstance().getFypCoordinator().get(0);
        Request_DeregisterFYP requestDeregisterFYP = new Request_DeregisterFYP(this.getID(), fypCoordinator.getID(), RequestType.DEREGISTER_FYP,project.getProjectID());
        this.addOutgoingRequest(requestDeregisterFYP);
        fypCoordinator.addIncomingRequest(requestDeregisterFYP);

        fypCoordinator.addRequest(requestDeregisterFYP);
    }

    /**
     * Overrides the method from the parent class User to print all outgoing requests made by the student.
     */
    @Override
    public void printAllRequest() {
        // Implement logic to return a list of all requests outgoing by Student
        System.out.println("ALL REQUESTS\n");
        List<Request> outgoingRequest = this.getOutgoingRequest();
        for (int i = 0; i < outgoingRequest.size(); i++) {
            System.out.println(outgoingRequest.get(i).toString());
        }
    }

    /**
     * Returns a boolean indicating if the student has any outgoing requests for a reserved project.
     *
     * @return true if the student has an outgoing request for a reserved project, false otherwise.
     */
    public boolean checkReserved(){
        List<Request> requests = this.getOutgoingRequest();
        for(Request request : requests){
            FYPDB fypdb = FYPDB.getInstance();
            if(fypdb.getProjectByID(request.getProjectID()).getStatus() == ProjectStatus.RESERVED || fypdb.getProjectByID(request.getProjectID()).getStatus() == ProjectStatus.ALLOCATED){
                return true;
            }
        }
        return false;
    }
}
