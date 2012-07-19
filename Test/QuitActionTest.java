import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class QuitActionTest {

    @Test
    public void shouldDisplayActionName(){
        Forum forum = new Forum();
        QuitAction action = new QuitAction(forum);

        assertThat(action.name(),equalTo("Quit"));
    }
}
