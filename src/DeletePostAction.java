public class DeletePostAction implements IForumAction{
    private Forum forum;

    public DeletePostAction(Forum forum) {
        this.forum = forum;
    }

    @Override
    public String name() {
        return "Delete Post";
    }

    @Override
    public void execute(IConsole console) {
        if(!forum.isLoggedIn() || !forum.isAdmin()){
            console.writeLine("You have to be an admin to continue.");
            return;
        }
        if(forum.viewPosts().size() == 0){
            console.writeLine("No posts exist.");
            return;
        }
        console.writeLine("Please select which post you would like to delete:");
        printListOfExistingPosts(console);
        String input = console.readLine();
        int selected = 0;
        try{
            selected = Integer.parseInt(input);
        }catch (NumberFormatException error){
            console.writeLine("Please enter a number");
            return;
        }
        --selected;
        if(selected >= forum.viewPosts().size()){
            console.writeLine("Please enter a valid input.");
            return;
        }
        forum.deletePost(selected);
        console.writeLine("The post has been successfully deleted.");
    }

    private void printListOfExistingPosts(IConsole console) {
        for (int i = 0 ; i < forum.viewPosts().size() ; i++) {
            printPostDetails(console, i);
        }
    }

    private void printPostDetails(IConsole console, int i) {
        console.writeLine((i+1) + ") " + forum.viewPosts().get(i).getDetails());
    }
}
