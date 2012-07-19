public class QuitAction implements IForumAction{

    private Forum forum;

    public QuitAction(Forum forum) {
        this.forum = forum;
    }

    @Override
    public String name() {
        return "Quit";
    }

    @Override
    public void execute(IConsole console) {

    }
}
