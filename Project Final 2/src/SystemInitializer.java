import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SystemInitializer {
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
