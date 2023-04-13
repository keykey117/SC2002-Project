import java.util.ArrayList;

public class Student implements Password{
    private String name;
    private String email;
    private String studentID;

    // include project implementation
    // private Project project;


    // constructor
    public Student(String name, String email, String studentID){
        this.name = name;
        this.email = email;
        this.studentID = studentID;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public String getStudentID(){
        return this.studentID;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }






}
