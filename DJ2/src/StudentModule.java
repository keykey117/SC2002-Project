import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentModule implements Module{


    private Student student;
    public StudentModule(Student student){
        this.student = student;
    };
    public void run(){
    Scanner sc = new Scanner(System.in);

    // Choices student can make
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
            // todo case 1 ->
            case 1 -> student.setPassword(student.getStudentID());
            // todo case 2 ->
            case 2 -> student.printAvailProj();
            // todo case 3 ->
            case 3 -> student.selectProj();
            // todo case 4 ->
            // todo case 5  ->
            // todo case 6 ->
            case 7 -> System.out.println("Signing out of student module...");
            default -> System.out.println("Invalid choice!");
        }
    }
}


}
