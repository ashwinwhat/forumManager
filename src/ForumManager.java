import java.util.ArrayList;
import java.util.List;

public class ForumManager {

    private List<IForumAction> actions;
    private IConsole console;
    private Forum forum;

    public ForumManager(IConsole console, Forum forum, List<IForumAction> actions) {
        this.console = console;
        this.forum = forum;
        this.actions = actions;
        run();
    }

    public void run() {
        console.writeLine("Please select which operation you would like to perform");
        for (int iterator = 1 ; iterator <= actions.size() ; iterator++) {
            console.writeLine(iterator + ") " + actions.get(iterator-1).name());
        }
        String input = console.readLine();
        int selected = 0;
        try{
            selected = Integer.parseInt(input);
            --selected;
        }catch (NumberFormatException error){
            console.writeLine("Please enter a number");
            return;
        }
        if(selected >= actions.size()){
            console.writeLine("Please enter a valid option");
            return;
        }
        if(selected == actions.size()-1){
            System.exit(0);
        }
        actions.get(selected).execute(console);
    }

    public static void main(String[] args) {
        MainConsole console = new MainConsole();
        Forum forum = new Forum();
        List<IForumAction> actions = new ArrayList<IForumAction>();
        actions.add(new LoginAction(forum));
        actions.add(new ViewPostsAction(forum));
        actions.add(new AddNewPostAction(forum));
        actions.add(new DeletePostAction(forum));
        actions.add(new QuitAction(forum));
        ForumManager manager = new ForumManager(console,forum,actions);
        while(true){
            manager.run();
        }
    }
}
