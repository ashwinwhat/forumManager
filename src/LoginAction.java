public class LoginAction implements IAction{

    private Forum forum;

    public LoginAction(Forum forum){
        this.forum = forum;
    }

    @Override
    public String name() {
        return "Login";
    }

    @Override
    public void execute(IConsole console) {
        console.writeLine("Enter Login ID:");
        String id = console.readLine();
        console.writeLine("Enter Password:");
        String password = console.readLine();
        try{
            forum.login(id,password);
        }catch ()
    }
}
