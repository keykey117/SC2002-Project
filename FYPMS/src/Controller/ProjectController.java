
package Controller;

import Entity.Project;
import Database.*;

public class ProjectController {

    public void changeTitle(int projectID, String newTitle) {
        Project project = getProjectByID(projectID);
        if (project != null) {
            project.setTitle(newTitle);
            System.out.println("Project title updated successfully.");
        } else {
            System.out.println("Invalid project ID.");
        }
    }

    public void changeSupervisor(int projectID, Supervisor newSupervisor) {
        Project project = getProjectByID(projectID);
        if (project != null) {
            project.setSupervisor(newSupervisor);
            System.out.println("Project supervisor updated successfully.");
        } else {
            System.out.println("Invalid project ID.");
        }
    }

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