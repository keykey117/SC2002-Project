import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentModule implements Module{
    /**
     * Creates a Scanner object to allow for user inputs
     */
    public void run(){
    Scanner sc = new Scanner(System.in);
    // Initialize module
    // todo

    // Menu of choices the customer can choose from
    int choice = -1;
        while (choice != 7) {
        System.out.println("\n--------------Student Panel--------------");
        System.out.println("Option Available: (1-6):");
        System.out.println("(1) Change Password");
        System.out.println("(2) View available projects");
        System.out.println("(3) Select Project to send to Coordinator");
        System.out.println("(4) View My Project");
        System.out.println("(5) View request status and history");
        System.out.println("(6) Deregister FYP");
        System.out.println("(7) Quit Student Panel");
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
            // todo case 1 -> allProjectsModule.run();
            // todo case 2 -> // todo specialOccasionModule.run();
            // todo case 3 -> // todo sysMovieModule.run();
            // todo case 4 -> // todo sysShowtimeModule.run();
            // todo case 5  -> // todo sysListingOptionsModule.run();
            // todo case 6 -> // todo
            case 7 -> System.out.println("Signing out of student module...");
            default -> System.out.println("Invalid choice!");
        }
    }
}


}
