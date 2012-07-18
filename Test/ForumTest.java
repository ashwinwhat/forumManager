import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class ForumTest {

    @Test
    public void shouldLetUserLogin(){
        Forum forum = new Forum();
        forum.login("default","password");

        assertThat(forum.isLoggedIn(), equalTo(true));
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowErrorIfIncorrectUserName(){
        Forum forum = new Forum();
        forum.login("incorrect","password");
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowErrorIfIncorrectPassword(){
        Forum forum = new Forum();
        forum.login("default","incorrect");
    }

    @Test
    public void shouldDisplayListOfExistingPosts(){
        Forum forum = new Forum();
        FakeConsole console = new FakeConsole();
        forum.login("default","password");
        Post post = new Post(console, "Test Title", "Dummy Author", "This is a dummy post.");
        forum.add(post);

        forum.viewPosts();
        assertThat(console.getOutput(),containsString("Test Title - Dummy Author"));
    }

    @Test
    public void shouldBeAllowedToCreatePostIfLoggedIn(){
        FakeConsole console = new FakeConsole();
        Forum forum = new Forum();
        forum.login("default","password");
        Post post = new Post(console, "Test Title", "Dummy Author", "This is a dummy post.");
        forum.add(post);

        forum.viewPosts();
        assertThat(console.getOutput(),containsString("Test Title - Dummy Author"));

    }

    @Test
    public void shouldReadSelectedPost(){
        Forum forum = new Forum();
        FakeConsole console = new FakeConsole();
        Post post = new Post(console, "Test Title", "Dummy Author", "This is a dummy post.");
        forum.login("default","password");
        forum.add(post);

        forum.readPost(post);

        assertThat(console.getOutput(), containsString("This is a dummy post."));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorIfIncorrectInputProvided(){
        Forum forum = new Forum();
        FakeConsole console = new FakeConsole();
        Post post = new Post(console, "Test Title", "Dummy Author", "This is a dummy post.");
        forum.login("default","password");
        forum.add(post);

        forum.readPost(new Post(console,"dummy title","",""));
    }

    @Test
    public void shouldBeAllowedToAddCommentIfLoggedIn(){
        Forum forum = new Forum();
        FakeConsole console = new FakeConsole();
        forum.login("default","password");
        Post post = new Post(console,"Test Title","Dummy Author","This is a dummy post.");
        forum.add(post);

        forum.comment(post,"This is a dummy comment");
        forum.readComments(post);
        assertThat(console.getOutput(), containsString("This is a dummy comment"));
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowErrorIfTryingToAddPostAndNotLoggedIn(){
        Forum forum = new Forum();
        FakeConsole console = new FakeConsole();
        Post post = new Post(console,"Test Title","Dummy Author","This is a dummy post.");
        forum.add(post);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowErrorIfTryingToAddCommentWithoutLogin(){
        Forum forum = new Forum();
        FakeConsole console = new FakeConsole();
        forum.login("default","password");
        Post post = new Post(console,"Test Title","Dummy Author","This is a dummy post.");
        forum.add(post);
        forum.logout();
        forum.comment(post,"This is a dummy comment");
    }
}