public class Request_ChangeSupervisor extends Request {

    private String replacementSupervisor;
    public Request_ChangeSupervisor(String SenderID, String ReceiverID, RequestType reqType, int projectID, String replacementSupervisor) {
        super(SenderID, ReceiverID, reqType, projectID);
        this.replacementSupervisor = replacementSupervisor;
    }

    //Only FYP coordinator can approve or reject
    @Override
    public void approve() {
        super.approve();
        //change supervisor for project
    }

    @Override
    public void reject() {
        super.reject();
    }
}