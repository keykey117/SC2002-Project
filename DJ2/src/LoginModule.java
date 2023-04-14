public class LoginModule implements Module{
    public void run(){
        LoginHandler loginHandler = new LoginHandler();
        User user = loginHandler.login();

        // Login fails
        if (user == null) {
            System.out.println("Login failed, exiting login module now");
            return;
        }
        switch (user.getUserRole()){
            case STUDENT:
                Student student = StudentDB.getInstance().getStudent(user.getUserID());
                StudentModule studentModule = new StudentModule(student);
                studentModule.run();
                break;
            case SUPERVISOR:
                Supervisor supervisor = SupervisorDB.getInstance().getSupervisor(user.getUserID());
                SupervisorModule supervisorModule = new SupervisorModule(supervisor);
                supervisorModule.run();
                break;
            case COORDINATOR: break;
            default: break;
        }


    }
}