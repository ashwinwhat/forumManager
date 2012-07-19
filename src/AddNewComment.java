/**
 * Created with IntelliJ IDEA.
 * User: ashwinve
 * Date: 7/18/12
 * Time: 11:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class AddNewComment implements IPostAction {
    private Forum forum;
    private Post post;

    public AddNewComment(Forum forum, Post post) {
        this.forum = forum;
        this.post = post;
    }

    @Override
    public String name() {
        return "Add Comment";
    }

    @Override
    public void execute(IConsole console) {
        if(!forum.isLoggedIn()){
            console.writeLine("You have to login to add a comment.");
            return;
        }
        console.writeLine("Please enter your comment:");
        String comment = console.readLine();
        post.comment(comment);
        console.writeLine("Comment successfully added.");
    }
}
