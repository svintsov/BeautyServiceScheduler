package command;

import bundle.MessageManager;
import javax.servlet.http.HttpServletRequest;

/**
 * Factory for retrieving command from request
 */
public class CommandFactory {

  private static final String PARAM_NAME_COMMAND="command";
  private static final String ATTRIBUTE_NAME_WRONG_ACTION="wrongAction";

  public Command defineCommand(final HttpServletRequest request) {
    Command current = new EmptyCommand();
    final String action = request.getParameter(PARAM_NAME_COMMAND);
    if (action == null || action.isEmpty()) {
      return current;
    }

    try {

      CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
      current = currentEnum.getCurrentCommand();

    } catch (IllegalArgumentException e) {
      request.setAttribute(ATTRIBUTE_NAME_WRONG_ACTION, action
          + MessageManager.getProperty("message.wrongaction"));
    }
    return current;
  }
}