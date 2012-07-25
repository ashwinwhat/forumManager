import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class DeletePostActionTest {

    @Test
    public void shouldDisplayNameOfAction(){
        Forum forum = new Forum();
        DeletePostAction action = new DeletePostAction(forum);

        assertThat(action.name(), equalTo("Delete Post"));
    }

    @Test
    public void shouldDisplayMessageIfNotLoggedIn(){
        Forum forum = new Forum();
        DeletePostAction action = new DeletePostAction(forum);
        FakeConsole console = new FakeConsole();
        console.addInputs("1");

        action.execute(console);

        assertThat(console.getOutput(),containsString("You have to be an admin to continue."));
    }

    @Test
    public void shouldDisplayMessagesIfNoPostsExist(){
        Forum forum = new Forum();
        forum.login("1","1");
        DeletePostAction action = new DeletePostAction(forum);
        FakeConsole console = new FakeConsole();
        console.addInputs("1");

        action.execute(console);

        assertThat(console.getOutput(),containsString("No posts exist."));
    }

    @Test
    public void shouldDisplayDeletePostMessage(){
        Forum forum = new Forum();
        forum.login("1","1");
        forum.add(new Post("dummy post", "1", "dummy content"));
        DeletePostAction action = new DeletePostAction(forum);
        FakeConsole console = new FakeConsole();
        console.addInputs("1");

        action.execute(console);

        assertThat(console.getOutput(),containsString("Please select which post you would like to delete:"));
    }

    @Test
    public void shouldDisplayTheListOfPosts(){
        Forum forum = new Forum();
        FakeConsole console = new FakeConsole();
        forum.login("1","1");
        forum.add(new Post("dummy post", "1", "dummy content"));
        forum.add(new Post("dummy post2","1","dummy content2"));
        DeletePostAction action = new DeletePostAction(forum);
        console.addInputs("1");

        action.execute(console);

        assertThat(console.getOutput(),containsString("1) dummy post - 1"));
        assertThat(console.getOutput(),containsString("2) dummy post2 - 1"));
    }

    @Test
    public void shouldDeleteSelectedPost(){
        Forum forum = new Forum();
        FakeConsole console = new FakeConsole();
        forum.login("1","1");
        forum.add(new Post("dummy post", "1", "dummy content"));
        forum.add(new Post("dummy post2","1","dummy content2"));
        DeletePostAction action = new DeletePostAction(forum);
        console.addInputs("1");

        action.execute(console);

        assertThat(console.getOutput(),containsString("The post has been successfully deleted."));
    }

}
