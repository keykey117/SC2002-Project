import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;

public class SupervisorModule {
    private Supervisor supervisor;

    public SupervisorModule(Supervisor supervisor){
        this.supervisor = supervisor;
    }

    public void run(){
        Scanner sc = new Scanner(System.in);

        // Choices supervisor can make
        int choice = -1;
        while (choice != 7) {
            System.out.println("\n--------------Supervisor Panel--------------");
            System.out.println("Option Available: (1-6):");
            System.out.println("(1) Change Password");
            System.out.println("(2) View own Projects");
            System.out.println("(3) Modify own Project Title");
            System.out.println("(4) Add new Project");
            System.out.println("(5) Request");
            System.out.println("(6) Enter FYP Module");
            System.out.println("(7) Quit Supervisor Panel");
            System.out.println("---------------------------------------");
            System.out.print("\nChoice: ");


            try{
                choice = sc.nextInt();
            } catch (InputMismatchException err){
                System.out.println("Error: Please input a valid number (1 - 6).\n");
                sc.nextLine();
                continue;
            }
            sc.nextLine();
            switch(choice) {
                case 1:
                    System.out.println("Enter old password: ");
                    String oldPassword = sc.nextLine();
                    if(!oldPassword.equals(supervisor.getPassword())){
                        System.out.println("Password does not match");
                        break;
                    }
                    System.out.println("Enter new password: ");
                    String newPassword = sc.nextLine();
                    supervisor.changePassword(newPassword);
                    break;
                case 2:
                    supervisor.viewProjects();
                    break;
                case 3:
                    supervisor.viewProjects();
                    System.out.println("Which project would you like to modify?");
                    int projectID = sc.nextInt();
                    String newTitle = "";

                    // check for their own project
                    List<Project> projects = FYPDB.getInstance().getProjects();
                    for(Project project : projects){
                        if(project.getProjectID() == projectID && project.getSupervisor().getID().equals(supervisor.getID())){
                            System.out.println("This is the project");
                            project.toString();
                            sc.nextLine();
                            System.out.println("Enter the new title:");
                            newTitle = sc.nextLine();
                            project.setTitle(newTitle);
                            break;
                        }
                    }
                    if (newTitle.isEmpty()) {
                        System.out.println("You have entered an invalid projectID");
                    }
                    break;
                case 4:
                    System.out.println("Enter the new project title:");
                    String projTitle = sc.nextLine();
                    supervisor.createProject(projTitle);
                    break;
                case 6:
                    if(supervisor instanceof FYPCoordinator){
                        FYPCoordinatorModule fypCoordinatorModule = new FYPCoordinatorModule((FYPCoordinator) supervisor);
                        fypCoordinatorModule.run();
                    }
                    break;
                case 7:
                    System.out.println("Signing out of supervisor module...");
                    break;
                default: 
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }
}
