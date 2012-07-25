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
        Post post = new Post("Test Title", "Dummy Author", "This is a dummy post.");
        forum.add(post);

        assertThat(forum.viewPosts().get(0).getDetails(),containsString("Test Title - Dummy Author"));
    }

    @Test
    public void shouldBeAllowedToCreatePostIfLoggedIn(){
        FakeConsole console = new FakeConsole();
        Forum forum = new Forum();
        forum.login("default","password");
        Post post = new Post("Test Title", "Dummy Author", "This is a dummy post.");
        forum.add(post);

        assertThat(forum.viewPosts().get(0).getDetails(),containsString("Test Title - Dummy Author"));
    }

    @Test
    public void shouldReadSelectedPost(){
        Forum forum = new Forum();
        FakeConsole console = new FakeConsole();
        Post post = new Post("Test Title", "Dummy Author", "This is a dummy post.");
        forum.login("default","password");
        forum.add(post);


        assertThat(forum.readPost(post), containsString("This is a dummy post."));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorIfIncorrectInputProvided(){
        Forum forum = new Forum();
        FakeConsole console = new FakeConsole();
        Post post = new Post("Test Title", "Dummy Author", "This is a dummy post.");
        forum.login("default","password");
        forum.add(post);

        forum.readPost(new Post("dummy title","",""));
    }

    @Test
    public void shouldBeAllowedToAddCommentIfLoggedIn(){
        Forum forum = new Forum();
        FakeConsole console = new FakeConsole();
        forum.login("default","password");
        Post post = new Post("Test Title","Dummy Author","This is a dummy post.");
        forum.add(post);

        forum.comment(post,"This is a dummy comment");
        assertThat(forum.readComments(post).get(0), containsString("This is a dummy comment"));
    }

    @Test
    public void shouldAllowAdminToDeletePost(){
        Forum forum = new Forum();
        FakeConsole console = new FakeConsole();
        forum.login("1","1");
        Post post = new Post("Test Title","Dummy Author","This is a dummy post.");
        forum.add(post);
        Post post2 = new Post("Test Title2","Dummy Author2","This is a dummy post2.");
        forum.add(post2);

        forum.deletePost(0);

        assertThat(forum.viewPosts().get(0).getDetails(),containsString("Test Title2 - Dummy Author2"));
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotAllowNonAdminToDeletePost(){
        Forum forum = new Forum();
        FakeConsole console = new FakeConsole();
        forum.login("default","password");
        Post post = new Post("Test Title","Dummy Author","This is a dummy post.");
        forum.add(post);
        Post post2 = new Post("Test Title2","Dummy Author2","This is a dummy post2.");
        forum.add(post2);

        forum.deletePost(0);

    }
}