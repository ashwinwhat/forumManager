import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Forum {
    private List<Post> posts = new ArrayList<Post>();
    private Map<String,String> users = new HashMap<String, String>();
    private boolean loggedIn = false;

    public Forum(){
        users.put("default","password");
    }

    public void add(Post post) {
        if(!loggedIn) throw new RuntimeException("You have to login to add a post.");
        posts.add(post);
    }

    public void viewPosts() {
        for (Post post : posts) {
            post.getDetails();
        }
    }

    public void readPost(Post post) {
        if(!posts.contains(post))throw new IllegalArgumentException();
        post.read();
    }

    public void comment(Post post, String comment) {
        if (!posts.contains(post)) throw new IllegalArgumentException();
        if(!loggedIn) throw new RuntimeException("You have to login to add a comment.");

        posts.get(posts.indexOf(post)).comment(comment);
    }

    public void readComments(Post post) {
        if (!posts.contains(post)) throw new IllegalArgumentException();

        post.viewComments();
    }

    public void login(String userName, String password) {
        if(!users.containsKey(userName)) throw new RuntimeException();
        if(users.get(userName) != password) throw new RuntimeException();

        loggedIn = true;
    }

    public void logout(){
        if(!isLoggedIn()) throw new RuntimeException();
        loggedIn = false;
    }

    public Boolean isLoggedIn() {
        return loggedIn;
    }
}
