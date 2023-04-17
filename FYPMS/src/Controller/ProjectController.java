
package Controller;

import Entity.Project;
import Database.*;

/**
 * The ProjectController class is responsible for managing the projects in the FYP system.
 * @author Nobel
 * @version 1.0.0 Apr 16, 2023
 */
public class ProjectController {

    /**
     * Changes the title of the project with the given ID.
     * @param projectID the ID of the project to change the title of
     * @param newTitle the new title of the project
     */
    public void changeTitle(int projectID, String newTitle) {
        Project project = getProjectByID(projectID);
        if (project != null) {
            project.setTitle(newTitle);
            System.out.println("Project title updated successfully.");
        } else {
            System.out.println("Invalid project ID.");
        }
    }

    /**
     * Changes the supervisor of the project with the given ID.
     * @param projectID the ID of the project to change the supervisor of
     * @param newSupervisor the new supervisor of the project
     */
    public void changeSupervisor(int projectID, Supervisor newSupervisor) {
        Project project = getProjectByID(projectID);
        if (project != null) {
            project.setSupervisor(newSupervisor);
            System.out.println("Project supervisor updated successfully.");
        } else {
            System.out.println("Invalid project ID.");
        }
    }

    /**
     * Gets the project with the given ID.
     * @param projectID the ID of the project to get
     * @return the project with the given ID, or null if no such project exists
     */
    public Project getProjectByID(int projectID) {
        // Assume there is a list of all projects called "allProjects"
        for (Project project : FYPDB.getInstance().getProjects()) {
            if (project.getProjectID() == projectID) {
                return project;
            }
        }
        return null;
    }

}