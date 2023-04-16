public class Supervisor extends User {
    private int projCount;

    public Supervisor(String name, String email, String userID) {
        super(name, email, userID);
        this.projCount = 0;
    }

    public int getProjCount() {
        return this.projCount;
    }

    public void addProjCount() {
        this.projCount++;
    }

    public void subProjCount() {
        this.projCount--;
    }

    // implement any additional methods unique to the Supervisor class here
}