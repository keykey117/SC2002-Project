package Database;

import Controller.Student;
import Controller.FYPCoordinator;
import Controller.Supervisor;
import Entity.Project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides static methods for initializing the system with data from external files.
 * @author Ian Sim
 * @version 1.0.0 Apr 16, 2023
 */
public class SystemInitializer {

    /**
     * Reads the provided 'student_list.txt' file and returns a list of Student objects.
     *
     * @param filePath The path to the file containing the list of students.
     * @return A List of Student objects representing the students in the file.
     */
    public static List<Student> initializeStudentList(String filePath) {
        // Implement logic to read the provided 'student_list.txt' file and return a list of Student objects
        List<Student> studentList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header line

            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                String name = data[0];
                String email = data[1];
                String userID = email.substring(0, email.indexOf('@'));

                Student student = new Student(name, email, userID);
                studentList.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return studentList;
    }

    /**
     * Reads the provided file containing the list of faculty members and returns a list of Supervisor objects.
     *
     * @param filePath The path to the file containing the list of faculty members.
     * @return A List of Supervisor objects representing the faculty members in the file.
     */
    public static List<Supervisor> initializeFacultyList(String filePath) {
        List<Supervisor> facultyList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header line

            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                String name = data[0];
                String email = data[1];
                String userID = email.substring(0, email.indexOf('@'));

                Supervisor supervisor = new Supervisor(name, email, userID);
                facultyList.add(supervisor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return facultyList;
    }


    /**
     * Reads the provided 'rolloverproject.xlsx' file and returns a list of Project objects.
     *
     * @param filePath The path to the file containing the list of projects.
     * @return A List of Project objects representing the projects in the file.
     */
    public static List<Project> initializeProjectList(String filePath) {
        // Implement logic to read the provided 'rolloverproject.xlsx' file and return a list of Project objects
        List<Project> projectList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header line

            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                int projectID = Integer.parseInt(data[0]);
                String supervisorName = data[1];
                String supEmail = data[2];
                Supervisor supervisor = new Supervisor(supervisorName, supEmail, supEmail.substring(0, supEmail.indexOf('@')));
                String title = data[3];

                Project project = new Project(projectID, supervisor,title);
                projectList.add(project);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return projectList;
    }

    /**
     * Reads the provided 'FYPCoordinators.xlsx' file and returns an FYPCoordinator object.
     *
     * @param filePath The path to the file containing the FYPCoordinator information.
     * @return A List of FYPCoordinator objects representing the FYPCoordinators in the file.
     */
    public static List<FYPCoordinator> initializeFYPCoordinatorList(String filePath) {
        // Implement logic to read the provided 'FYPCoordinators.xlsx' file and return an FYPCoordinator object
        List<FYPCoordinator> fypCoordinatorList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header line

            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                String name = data[0];
                String email = data[1];
                String userID = email.substring(0, email.indexOf('@'));

                FYPCoordinator fypCoordinator = new FYPCoordinator(name,email, userID);
                fypCoordinatorList.add(fypCoordinator);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fypCoordinatorList;
    
    }
}
