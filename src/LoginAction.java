public class LoginAction implements IForumAction {

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
            console.writeLine("Login Successful.");
        }catch (RuntimeException exception){
            console.writeLine("Incorrect User Id or Password entered.");
        }
    }
}
