import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class PostTest {

    FakeConsole console = new FakeConsole();

    @Test
    public void shouldDisplayDetailsOfPost(){
        Post post = new Post(console,"Test Title", "Dummy Author","This is a dummy post");

        post.getDetails();
        assertThat(console.getOutput(),equalTo("Test Title - Dummy Author"));
    }

    @Test
    public void shouldAllowViewingThePost(){
        Post post = new Post(console,"Test Title","Dummy Author","This is a dummy post.");

        post.read();
        assertThat(console.getOutput(),equalTo("This is a dummy post."));
    }

    @Test
    public void shouldProvideTitleOfPost(){
        Post post = new Post(console,"Test Title","Dummy Author","This is a dummy post.");

        assertThat(post.getTitle(),equalTo("Test Title"));
    }

    @Test
    public void shouldAllowWritingOfComments(){
        Post post = new Post(console,"Test Title","Dummy Author","This is a dummy post.");

        post.comment("Dummy comment.");
        post.viewComments();

        assertThat(console.getOutput(), containsString("Dummy comment."));
    }
}
