import java.util.ArrayList;
import java.util.List;

public class ViewPostsAction implements IForumAction {

    private Forum forum;
    private List<IPostAction> actions = new ArrayList<IPostAction>();
    public ViewPostsAction(Forum forum){
        this.forum = forum;
    }

    @Override
    public String name() {
        return "Read Posts";
    }

    @Override
    public void execute(IConsole console) {
        console.writeLine("Following are the list of posts:");
        Post post = performPostActions(console);
        if(forum.viewPosts().isEmpty()){
            return;
        }
        if(actions.isEmpty()){
        installCommentActions(post);
        }
        console.writeLine("Which action would you like to perform:");
        performCommentActions(console);
    }

    private void performCommentActions(IConsole console) {
        PrintLisOfCommentActions(console);
        int selected = getUserChoice(console);
        performSelectedCommentAction(console, selected);
    }

    private void performSelectedCommentAction(IConsole console, int selected) {
        if(selected >= actions.size()) {
            console.writeLine("Please select a valid option.");
            return;
        }
        actions.get(selected).execute(console);
    }

    private Post performPostActions(IConsole console) {
        if(forum.viewPosts().isEmpty()){
            console.writeLine("No posts to display");
            return null;
        }
        printListOfPosts(console);
        int selected = getUserChoice(console);
        return printSelectedPost(console, selected);
    }

    private void PrintLisOfCommentActions(IConsole console) {
        for(int iterator = 0 ; iterator < actions.size() ; iterator++){
            console.writeLine((iterator+1) + ") " + actions.get(iterator).name());
        }
    }

    private void installCommentActions(Post post) {
        actions.add(new ViewCommentsAction(forum,post));
        actions.add(new AddNewComment(forum,post));
    }

    private Post printSelectedPost(IConsole console, int selected) {
        if(selected >= forum.viewPosts().size()){
            console.writeLine("Please enter a valid input");
            return null;
        }
        console.writeLine(forum.viewPosts().get(selected).read());
        return forum.viewPosts().get(selected);
    }

    private int getUserChoice(IConsole console) {
        console.writeLine("Please select which post you would like to read:");
        String choice = console.readLine();
        int selected = Integer.parseInt(choice);
        --selected;
        return selected;
    }

    private void printListOfPosts(IConsole console) {
        for(int iterator = 0 ; iterator < forum.viewPosts().size() ; iterator++){
            console.writeLine((iterator+1)+ ") " + forum.viewPosts().get(iterator).getDetails());
        }
    }
}
