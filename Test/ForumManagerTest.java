import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class ForumManagerTest {

    @Test
    public void shouldDisplaySelectAnActionMessage(){
        FakeConsole console = new FakeConsole();
        Forum forum = new Forum();
        List<IForumAction> actions = new ArrayList<IForumAction>();
        ForumManager manager = new ForumManager(console,forum,actions);

        assertThat(console.getOutput(),containsString("Please select which operation you would like to perform"));
    }

    @Test
    public void shouldDisplayListOfActions(){
        FakeConsole console = new FakeConsole();
        Forum forum = new Forum();
        List<IForumAction> actions = new ArrayList<IForumAction>();
        actions.add(createNewAction());
        actions.add(createNewAction());
        console.addInputs("1");
        ForumManager manager = new ForumManager(console,forum,actions);

        assertThat(console.getOutput(),containsString("1) Action"));
        assertThat(console.getOutput(),containsString("2) Action"));
    }

    @Test
    public void shouldPerformSelectedAction(){
        FakeConsole console = new FakeConsole();
        Forum forum = new Forum();
        List<IForumAction> actions = new ArrayList<IForumAction>();
        actions.add(createNewAction());
        actions.add(createNewAction());
        console.addInputs("1");
        ForumManager manager = new ForumManager(console,forum,actions);

        assertThat(console.getOutput(),containsString("Action Executed"));
    }

    private IForumAction createNewAction() {
        IForumAction action = new IForumAction() {
            @Override
            public String name() {
                return "Action";
            }

            @Override
            public void execute(IConsole console) {
                console.writeLine("Action Executed");
            }
        };
        return action;
    }
}
