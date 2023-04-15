import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;

public class StudentModule {
    private Student student;
    public StudentModule(Student student){
        this.student = student;
    }
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
            List<Project> projects;
            switch(choice){

                case 1: {
                    System.out.println("Enter old password: ");
                    String oldPassword = sc.nextLine();
                    if (!oldPassword.equals(student.getPassword())) {
                        System.out.println("Password does not match");
                        break;
                    }
                    System.out.println("Enter new password: ");
                    String newPassword = sc.nextLine();
                    student.changePassword(newPassword);
                    break;
                }

                case 2:
                    student.viewAvailableProjects();
//                {
//                    projects = FYPDB.getInstance().getProjects();
//                    for (Project project : projects) {
//                        if (project.getStatus() == ProjectStatus.AVAILABLE) {
//                            System.out.println(project.toString());
//                        }
//                    }
//                }
                    break;
                case 4:
                    try {
                        student.getProject().toString();
                    }
                    catch(Exception e){
                        System.out.println("Error occurred: " + e.getMessage());
                    }
                    break;
//                    projects = FYPDB.getInstance().getProjects();
//                    for (Project project : projects) {
//                        if (project.getStudent().getID().equals(student.getID())) {
//                            System.out.println(project.toString());
//                        }
//                    }

                case 7: System.out.println("Signing out of student module...");
                default: System.out.println("Invalid choice!");
            }
        }
        
    }
}
