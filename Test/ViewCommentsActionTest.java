import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class ViewCommentsActionTest {

    @Test
    public void shouldDisplayNameOfAction(){
        Forum forum = new Forum();
        Post post = new Post("Dummy Title","Dummy Author","Dummy content.");

        ViewCommentsAction action = new ViewCommentsAction(forum,post);

        assertThat(action.name(),equalTo("View Comments"));
    }

    @Test
    public void shouldDisplayCommentsOnAPost(){
        Forum forum = new Forum();
        FakeConsole console = new FakeConsole();
        Post post = new Post("Dummy Title","Dummy Author","Dummy content.");
        post.comment("mock comment");
        forum.login("default","password");
        forum.add(post);
        forum.logout();
        ViewCommentsAction action = new ViewCommentsAction(forum,post);

        action.execute(console);

        assertThat(console.getOutput(),containsString("mock comment"));
    }
}
