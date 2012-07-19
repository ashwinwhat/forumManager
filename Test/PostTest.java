import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class PostTest {

    FakeConsole console = new FakeConsole();

    @Test
    public void shouldDisplayDetailsOfPost(){
        Post post = new Post("Test Title", "Dummy Author","This is a dummy post");

        assertThat(post.getDetails(),equalTo("Test Title - Dummy Author"));
    }

    @Test
    public void shouldAllowViewingThePost(){
        Post post = new Post("Test Title","Dummy Author","This is a dummy post.");

        assertThat(post.read(),equalTo("This is a dummy post."));
    }

    @Test
    public void shouldProvideTitleOfPost(){
        Post post = new Post("Test Title","Dummy Author","This is a dummy post.");

        assertThat(post.getTitle(),equalTo("Test Title"));
    }

    @Test
    public void shouldAllowWritingOfComments(){
        Post post = new Post("Test Title","Dummy Author","This is a dummy post.");

        post.comment("Dummy comment.");

        assertThat(post.viewComments().get(0), containsString("Dummy comment."));
    }
}
