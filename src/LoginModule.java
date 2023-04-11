public class LoginModule implements Module{
    public void run(){
        LoginHandler loginHandler = new LoginHandler();
        User user = loginHandler.login();

        switch (user.getUserRole()){
            case STUDENT:
                Student student = StudentDB.getInstance().getStudent(user.getUserID());
                StudentModule studentModule = new StudentModule(student);
                studentModule.run();
                break;
            case SUPERVISOR: break;
            case COORDINATOR: break;
            default: break;
        }


    }
}
