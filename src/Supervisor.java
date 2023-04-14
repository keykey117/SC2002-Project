import java.util.ArrayList;

public class Supervisor {
    private String name;
    private String email;
    private String supervisorID;

    // include project implementation

    // constructor
    public Supervisor(String name, String email, String supervisorID){
        this.name = name;
        this.email = email;
        this.supervisorID = supervisorID;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public String getSupervisorID(){
        return this.supervisorID;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }






}
