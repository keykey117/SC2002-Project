public class LoginModule implements Module{
    public void run(){
        LoginHandler loginHandler = new LoginHandler();
        Credential credential = loginHandler.login();

        // Login fails
        if (credential == null) {
            System.out.println("Login failed, exiting login module now");
            return;
        }
        switch (credential.getRole()){
            case STUDENT:
                Student student = StudentDB.getInstance().getStudent(credential.getUsername());
                StudentModule studentModule = new StudentModule(student);
                studentModule.run();
                break;
            case SUPERVISOR:
                Supervisor supervisor = SupervisorDB.getInstance().getSupervisor(credential.getUsername());
                SupervisorModule supervisorModule = new SupervisorModule(supervisor);
                supervisorModule.run();
                break;
            case COORDINATOR: break;
            default: break;
        }


    }
}
