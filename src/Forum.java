import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Forum {
    private List<Post> posts = new ArrayList<Post>();
    private Map<String,String> users = new HashMap<String, String>();
    private boolean loggedIn = false;
    private String userName = null;

    public Forum(){
        users.put("default","password");
        users.put("1","1");
    }

    public void add(Post post) {
        posts.add(post);
    }

    public List<Post> viewPosts() {
        return posts;
    }

    public String readPost(Post post) {
        if(!posts.contains(post))throw new IllegalArgumentException();
        return post.read();
    }

    public void comment(Post post, String comment) {
        if (!posts.contains(post)) throw new IllegalArgumentException();

        posts.get(posts.indexOf(post)).comment(comment);
    }

    public List<String> readComments(Post post) {
        if (!posts.contains(post)) throw new IllegalArgumentException();

        return post.viewComments();
    }

    public String getUserName() {
        return userName;
    }

    public void login(String userName, String password) {
        if(!users.containsKey(userName)) throw new RuntimeException();
        if(!users.get(userName).equals(password)) throw new RuntimeException();
        this.userName = userName;
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
