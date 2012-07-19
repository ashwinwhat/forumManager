public class ViewCommentsAction implements IPostAction {

    private Forum forum;
    private Post post;

    public ViewCommentsAction(Forum forum, Post post) {
        this.forum = forum;
        this.post = post;
    }

    @Override
    public String name() {
        return "View Comments";
    }

    @Override
    public void execute(IConsole console) {
        if(post.viewComments().size() == 0){
            console.writeLine("No comments added yet");
            return;
        }
        for(String comment : post.viewComments()){
            console.writeLine(comment);
        }
    }
}
