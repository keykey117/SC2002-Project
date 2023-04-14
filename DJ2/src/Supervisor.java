import java.util.ArrayList;

public class Supervisor implements Password, ProjectHandler{
    private String name;
    private String email;
    private String supervisorID;

    private int projCount;

    // include project implementation

    // constructor
    public Supervisor(String name, String email, String supervisorID){
        this.name = name;
        this.email = email;
        this.supervisorID = supervisorID;
        this.projCount = 0;
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

    public int getProjCount(){ return this.projCount;}

    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }


    public void addProjCount(){this.projCount++;}
    public void subProjCount(){this.projCount--;}

}
