package command;

import javax.servlet.http.HttpServletRequest;

public interface Command {

  String execute(final HttpServletRequest request);
}
