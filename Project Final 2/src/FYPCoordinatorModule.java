import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FYPCoordinatorModule{
    private FYPCoordinator fypCoordinator;

    public FYPCoordinatorModule(FYPCoordinator fypCoordinator){
        this.fypCoordinator = fypCoordinator;
    }

    public void run(){
        Scanner sc = new Scanner(System.in);

        // Choices supervisor can make
        int choice = -1;
        while (choice != 7) {
            System.out.println("\n--------------FYP Coordinator Panel--------------");
            System.out.println("Option Available: (1-6):");
            System.out.println("(1) View all projects");
            System.out.println("(2) Change supervisor");
            System.out.println("(3) Allocate Project to Student");
            System.out.println("(4) Deregister project");
            System.out.println("(5) Filter and Generate Project Details Report");
            System.out.println("(6) View all pending request");
            System.out.println("(7) Quit FYP Panel");
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

            switch(choice){
                case 1:
                    fypCoordinator.viewAllProjects();
                    break;
                case 2:
                    break;
                case 4:
                    break;
                case 7: System.out.println("Signing out of student module...");
                default: System.out.println("Invalid choice!");
            }

        }
    }
}