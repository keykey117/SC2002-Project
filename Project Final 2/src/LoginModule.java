import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;

public class LoginModule{

    Scanner sc = new Scanner(System.in);

    public void run(){
        int userType;
        do {
            System.out.println("\n----------------------------");
            System.out.println("\nPlease enter 1 for student, 2 for Supervisor, 3 for FYPCoordinator");
            try {
                userType = sc.nextInt();
                if (userType < 1 || userType > 3) {
                    System.out.println("Invalid option. Please enter a number between 1 and 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
                sc.next(); // Consume the invalid input to prevent an infinite loop
                userType = 0; // Set to an invalid value to trigger the loop again
            }
        } while (userType < 1 || userType > 3);

        sc.nextLine();
        System.out.println("\n----------------------------");
        System.out.println("Enter your username:");
        String username = sc.nextLine();

        // Consume the newline character left in the buffer
        // sc.nextLine();

        System.out.println("\n----------------------------");
        System.out.println("Enter your password:");
        String password = sc.nextLine();

        // System.out.println("Hello World");
        // System.out.println(username);
        // System.out.println(password);


        // Check password

        switch(userType){
            case 1:
                List<Student> studentList = FYPDB.getInstance().getStudents();
                StudentModule studentModule = null;
                for (Student student : studentList){
                    // String currentID = student.getID();
                    // System.out.println(currentID);
                    // System.out.println(username);
                    if(username.equals(student.getID()) && password.equals(student.getPassword())){
                        // System.out.println("Testing Works");
                        studentModule = new StudentModule(student);
                        studentModule.run();
                    }
                }
                if(studentModule == null){
                    System.out.println("Wrong password entered.");
                }
                break;
            case 2:
                List<Supervisor> supervisorList = FYPDB.getInstance().getSupervisors();
                SupervisorModule supervisorModule = null;
                for (Supervisor supervisor: supervisorList){
                    if(username.equals(supervisor.getID()) && password.equals(supervisor.getPassword())){
                        supervisorModule = new SupervisorModule(supervisor);
                        supervisorModule.run();
                        // System.out.println("Testing Works");
                        // studentInterface.run();
                    }
                }
                break;
            case 3:
                List<FYPCoordinator> fypCoordinatorList = FYPDB.getInstance().getFypCoordinator();
                for (FYPCoordinator fypCoordinator: fypCoordinatorList){
//                    System.out.println(fypCoordinator.getID());
//                    System.out.println(username);
//                    System.out.println(fypCoordinator.getPassword());
//                    System.out.println(password);
                    if(username.equals(fypCoordinator.getID()) && password.equals(fypCoordinator.getPassword())){
//                        System.out.println("Testing Works");
                        supervisorModule = new SupervisorModule(fypCoordinator);
                        supervisorModule.run();
                        // studentInterface.run();
                    }
                }
                break;
            default:
                System.out.println("Invalid choice!");
            }
        }

        


        // try{
        //     System.out.println("\n----------------------------");
        //     System.out.println("\nPlease enter UserID:");
        //     String username = sc.nextLine();
        //     System.out.println("Please enter Password:");
        //     String password = sc.nextLine();
        //     System.out.println("----------------------------");
        // }
        
        // LoginHandler loginHandler = new LoginHandler();
        // Credential credential = loginHandler.login();

        // // Login fails
        // if (credential == null) {
        //     System.out.println("Login failed, exiting login module now");
        //     return;
        // }
        // switch (credential.getRole()){
        //     case STUDENT:
        //         Student student = StudentDB.getInstance().getStudent(credential.getUsername());
        //         StudentModule studentModule = new StudentModule(student);
        //         studentModule.run();
        //         break;
        //     case SUPERVISOR:
        //         Supervisor supervisor = SupervisorDB.getInstance().getSupervisor(credential.getUsername());
        //         SupervisorModule supervisorModule = new SupervisorModule(supervisor);
        //         supervisorModule.run();
        //         break;
        //     case COORDINATOR: break;
        //     default: break;
        // }

}
