import java.util.ArrayList;
import java.util.List;

public class Post {
    private String author;
    private String title;
    private String content;
    private List<String> comments = new ArrayList<String>();
    private IConsole console;

    public Post(IConsole console,String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.console = console;
    }

    public void getDetails() {
        console.writeLine(title + " - " + author);
    }

    public void read() {
        console.writeLine(content);
    }

    public void comment(String comment) {
        comments.add(comment);
    }

    public void viewComments() {
        for (String comment : comments) {
            console.writeLine(comment);
        }
    }

    public String getTitle() {
        return title;
    }
}