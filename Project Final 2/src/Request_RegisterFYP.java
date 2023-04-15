//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class Request_RegisterFYP extends Request {
    public Request_RegisterFYP(String SenderID, String ReceiverID, RequestType reqType, int projectID) {
        super(SenderID, ReceiverID, reqType, projectID);
    }

    public void approve() {
        super.approve();
        //TODO find student object using studentID
        //allocate project to student
        //TODO find project object using projectID
        //mark project as allocated
    }

    public void reject() {
        super.reject();
    }
}
