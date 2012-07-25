import java.util.ArrayList;
import java.util.List;

public class Post {
    private String author;
    private String title;
    private String content;
    private List<String> comments = new ArrayList<String>();

    public Post(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public String getDetails() {
        return(title + " - " + author);
    }

    public String read() {
        return content;
    }

    public void comment(String comment) {
        comments.add(comment);
    }

    public List<String> viewComments() {
        return comments;
    }

    public String getTitle() {
        return title;
    }

    public void deleteComment(int commentNumber) {
        comments.remove(commentNumber);
    }
}