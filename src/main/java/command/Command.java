package command;

import javax.servlet.http.HttpServletRequest;

/**
 * Basic interface for Command pattern
 */
public interface Command {

  String execute(final HttpServletRequest request);
}
