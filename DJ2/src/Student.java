public class Student extends User {
    private Project project;

    public Student(String name, String email, String userID) {
        super(name, email, userID);
        this.project = null;
    }

    // implement any additional methods unique to the Student class here
}