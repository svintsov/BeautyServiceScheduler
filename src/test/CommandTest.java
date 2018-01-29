import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.instanceOf;

import command.CommandEnum;
import command.action.FinishVisitCommand;
import org.junit.Assert;
import org.junit.Test;

public class CommandTest {

  @Test
  public void requestParameterTest() {

    Assert.assertThat(CommandEnum.valueOf("finish_visit".toUpperCase()),
        is(CommandEnum.FINISH_VISIT));
  }

  @Test
  public void checkCommandTest() {

    CommandEnum commandMock = mock(CommandEnum.class);
    when(commandMock.getCurrentCommand()).thenReturn(new FinishVisitCommand());
    Assert.assertThat(CommandEnum.FINISH_VISIT.getCurrentCommand(),
        instanceOf(commandMock.getCurrentCommand().getClass()));
  }

}
