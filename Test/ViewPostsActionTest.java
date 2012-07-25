import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class ViewPostsActionTest {

    @Test
    public void shouldDisplayActionName(){
        Forum forum = new Forum();
        ViewPostsAction action = new ViewPostsAction(forum);

        assertThat(action.name(), equalTo("Read Posts"));
    }

    @Test
    public void shouldDisplayListOfPostsMessage(){
        Forum forum = new Forum();
        FakeConsole console = new FakeConsole();
        Post post = new Post("Dummy Title","Dummy Author","This is a dummy post.");
        forum.login("default", "password");
        forum.add(post);
        forum.logout();
        console.addInputs("1","1");
        ViewPostsAction action = new ViewPostsAction(forum);

        action.execute(console);

        assertThat(console.getOutput(),containsString("Following are the list of posts:"));
    }

    @Test
    public void shouldDisplayPostList(){
        Forum forum = new Forum();
        FakeConsole console = new FakeConsole();
        Post post = new Post("Dummy Title","Dummy Author","This is a dummy post.");
        forum.login("default","password");
        forum.add(post);
        forum.logout();
        console.addInputs("1","1");
        ViewPostsAction action = new ViewPostsAction(forum);

        action.execute(console);

        assertThat(console.getOutput(),containsString("1) Dummy Title - Dummy Author"));
    }

    @Test
    public void shouldAskUserForChoiceOfPost(){
        Forum forum = new Forum();
        FakeConsole console = new FakeConsole();
        Post post = new Post("Dummy Title","Dummy Author","This is a dummy post.");
        forum.login("default","password");
        forum.add(post);
        forum.logout();
        console.addInputs("1","1");
        ViewPostsAction action = new ViewPostsAction(forum);

        action.execute(console);

        assertThat(console.getOutput(),containsString("Please select which post you would like to read:"));
    }

    @Test
    public void shouldDisplaySelectedPost(){
        Forum forum = new Forum();
        FakeConsole console = new FakeConsole();
        Post post = new Post("Dummy Title","Dummy Author","This is a dummy post.");
        forum.login("default","password");
        forum.add(post);
        forum.logout();
        console.addInputs("1","1");
        ViewPostsAction action = new ViewPostsAction(forum);

        action.execute(console);

        assertThat(console.getOutput(),containsString("This is a dummy post."));
    }

    @Test
    public void shouldThrowExceptionIfIncorrectChoiceEntered(){
        Forum forum = new Forum();
        FakeConsole console = new FakeConsole();
        Post post = new Post("Dummy Title","Dummy Author","This is a dummy post.");
        forum.login("default","password");
        forum.add(post);
        forum.logout();
        console.addInputs("3");
        ViewPostsAction action = new ViewPostsAction(forum);

        action.execute(console);

        assertThat(console.getOutput(),containsString("Please enter a valid option"));
    }

    @Test
    public void shouldAskUserToChooseCommentOptions(){
        Forum forum = new Forum();
        FakeConsole console = new FakeConsole();
        Post post = new Post("Dummy Title","Dummy Author","This is a dummy post.");
        forum.login("default","password");
        forum.add(post);
        forum.logout();
        console.addInputs("1","1");
        ViewPostsAction action = new ViewPostsAction(forum);

        action.execute(console);

        assertThat(console.getOutput(),containsString("Which action would you like to perform:"));
    }

    @Test
    public void shouldDisplayListOfActions(){
        Forum forum = new Forum();
        FakeConsole console = new FakeConsole();
        Post post = new Post("Dummy Title","Dummy Author","This is a dummy post.");
        forum.login("default","password");
        forum.add(post);
        forum.logout();
        console.addInputs("1","1");
        ViewPostsAction action = new ViewPostsAction(forum);

        action.execute(console);

        assertThat(console.getOutput(),containsString("1) Add Comment"));
    }

    @Test
    public void shouldDisplayMessageIfWrongChoiceSelected(){
        Forum forum = new Forum();
        FakeConsole console = new FakeConsole();
        Post post = new Post("Dummy Title","Dummy Author","This is a dummy post.");
        forum.login("default","password");
        forum.add(post);
        forum.logout();
        console.addInputs("1","3");
        ViewPostsAction action = new ViewPostsAction(forum);

        action.execute(console);

        assertThat(console.getOutput(),containsString("Please select a valid option."));
    }
}
