import java.util.InputMismatchException;
import java.util.Scanner;

public class SupervisorModule implements Module{

    private Supervisor supervisor;
    public SupervisorModule(Supervisor supervisor){
        this.supervisor = supervisor;
    };
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

            switch (choice) {
                // todo case 1 ->
                // todo case 2 ->
                // todo case 3 ->
                case 4 -> System.out.println("Signing out of supervisor module...");
                default -> System.out.println("Invalid choice!");
            }
        }
    }


}
