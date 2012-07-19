import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class LoginActionTest {

    @Test
    public void shouldDisplayCorrectNameOfAction(){
        Forum forum = new Forum();
        LoginAction action = new LoginAction(forum);

        assertThat(action.name(),equalTo("Login"));
    }

    @Test
    public void shouldAskForUserName(){
        Forum forum = new Forum();
        FakeConsole console = new FakeConsole();
        console.addInputs("default","password");
        LoginAction action = new LoginAction(forum);

        action.execute(console);

        assertThat(console.getOutput(),containsString("Enter Login ID:"));
    }

    @Test
    public void shouldAskForPassword(){
        Forum forum = new Forum();
        FakeConsole console = new FakeConsole();
        console.addInputs("default","password");
        LoginAction action = new LoginAction(forum);

        action.execute(console);

        assertThat(console.getOutput(),containsString("Enter Password:"));
    }

    @Test
    public void shouldSendMessageIfIncorrectIdOrPasswordEntered(){
        Forum forum = new Forum();
        FakeConsole console = new FakeConsole();
        console.addInputs("default","psword");
        LoginAction action = new LoginAction(forum);

        action.execute(console);

        assertThat(console.getOutput(),containsString("Incorrect User Id or Password entered."));
    }

    @Test
    public void shouldSendMessageIfCorrectIdAndPasswordEntered(){
        Forum forum = new Forum();
        FakeConsole console = new FakeConsole();
        console.addInputs("default","password");
        LoginAction action = new LoginAction(forum);

        action.execute(console);

        assertThat(console.getOutput(),containsString("Login Successful"));
    }
}
