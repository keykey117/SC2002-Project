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
        while (choice != 4) {
            System.out.println("\n--------------Supervisor Panel--------------");
            System.out.println("Option Available: (1-6):");
            System.out.println("(1) Change Password");
            System.out.println("(2) Projects");
            System.out.println("(3) Request");
            System.out.println("(4) Quit Supervisor Panel");
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
            List<Project> projects;
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
                    projects = FYPDB.getInstance().getProjects();
                    for (Project project : projects){
                        if(project.getSupervisor().getID().equals(supervisor.getID())){
                            System.out.println(project.toString());
                        }
                    }
                    break;
                case 4: 
                    System.out.println("Signing out of supervisor module...");
                    break;
                default: 
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }
}
