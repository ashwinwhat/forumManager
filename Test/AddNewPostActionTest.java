import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class AddNewPostActionTest {

    @Test
    public void shouldDisplayActionName(){
        Forum forum = new Forum();
        AddNewPostAction action = new AddNewPostAction(forum);

        assertThat(action.name(),equalTo("Add New Post"));
    }

    @Test
    public void shouldNotAcceptPostForUserWhenNotLoggedIn(){
        Forum forum = new Forum();
        FakeConsole console = new FakeConsole();
        AddNewPostAction action = new AddNewPostAction(forum);

        action.execute(console);

        assertThat(console.getOutput(),containsString("You have to login to add a post."));
    }

    @Test
    public void shouldAskUserForTitleOfPost(){
        Forum forum = new Forum();
        FakeConsole console = new FakeConsole();
        forum.login("default","password");
        console.addInputs("Test Title","This is a dummy post");
        AddNewPostAction action = new AddNewPostAction(forum);

        action.execute(console);

        assertThat(console.getOutput(),containsString("Please enter title of post:"));
    }

    @Test
    public void shouldAskUserForContentOfPost(){
        Forum forum = new Forum();
        FakeConsole console = new FakeConsole();
        forum.login("default","password");
        console.addInputs("Test Title","This is a dummy post");
        AddNewPostAction action = new AddNewPostAction(forum);

        action.execute(console);

        assertThat(console.getOutput(),containsString("Please enter content of post:"));
    }

    @Test
    public void shouldSavePostToForum(){
        Forum forum = new Forum();
        FakeConsole console = new FakeConsole();
        forum.login("default","password");
        console.addInputs("Test Title","This is a dummy post");
        AddNewPostAction action = new AddNewPostAction(forum);

        action.execute(console);

        assertThat(forum.viewPosts().get(0).getDetails(),containsString("Test Title - default"));
    }

}
