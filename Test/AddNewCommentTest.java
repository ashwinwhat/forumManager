import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class AddNewCommentTest {

    @Test
    public void shouldDisplayNameOfAction(){
        Forum forum = new Forum();
        Post post = new Post("Dummy Title","Dummy Author","Dummy content.");

        AddNewComment action = new AddNewComment(forum,post);

        assertThat(action.name(),equalTo("Add Comment"));
    }

    @Test
    public void shouldNotAcceptPostForUserWhenNotLoggedIn(){
        Forum forum = new Forum();
        FakeConsole console = new FakeConsole();
        Post post = new Post("Dummy Title","Dummy Author","Dummy content.");
        forum.login("default","password");
        forum.add(post);
        forum.logout();
        AddNewComment action = new AddNewComment(forum,post);

        action.execute(console);

        assertThat(console.getOutput(),containsString("You have to login to add a comment."));
    }

    @Test
    public void shouldAskUserForTitleOfPost(){
        Forum forum = new Forum();
        FakeConsole console = new FakeConsole();
        forum.login("default","password");
        console.addInputs("Test Title","This is a dummy post");
        Post post = new Post("Dummy Title","Dummy Author","Dummy content.");
        forum.login("default","password");
        forum.add(post);
        AddNewComment action = new AddNewComment(forum,post);

        action.execute(console);

        assertThat(console.getOutput(),containsString("Please enter your comment:"));
    }

}
