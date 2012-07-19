public class AddNewPostAction implements IForumAction {
    private Forum forum;

    public AddNewPostAction(Forum forum) {
        this.forum = forum;
    }

    @Override
    public String name() {
        return "Add New Post";
    }

    @Override
    public void execute(IConsole console) {
        if(!forum.isLoggedIn()){
            console.writeLine("You have to login to add a post.");
            return;
        }
        console.writeLine("Please enter title of post:");
        String title = console.readLine();
        console.writeLine("Please enter content of post:");
        String content = console.readLine();
        forum.add(new Post(title,forum.getUserName(),content));
        console.writeLine("New Post has been added.");
    }
}
