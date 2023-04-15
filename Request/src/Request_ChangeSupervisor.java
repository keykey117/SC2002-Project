//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class Request_ChangeSupervisor extends Request {
    private String replacementSupervisor;

    public Request_ChangeSupervisor(String SenderID, String ReceiverID, RequestType reqType, int projectID, String replacementSupervisor) {
        super(SenderID, ReceiverID, reqType, projectID);
        this.replacementSupervisor = replacementSupervisor;
    }

    public void approve() {
        super.approve();
    }

    public void reject() {
        super.reject();
    }
}
