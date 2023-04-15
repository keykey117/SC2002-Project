public class Request {
    private int id;
    private User fromUser;
    private User toUser;
    private String type;
    private String status;

    public Request(int id, User fromUser, User toUser, String type, String status) {
        this.id = id;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.type = type;
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String newStatus) {
        this.status = newStatus;
    }
}