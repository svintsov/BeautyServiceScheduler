package command.action;

import bundle.ConfigurationManager;
import command.Command;
import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {

  @Override
  public String execute(final HttpServletRequest request) {
    final String page = ConfigurationManager.getProperty("path.page.index");
    request.getSession().invalidate();
    return page;
  }
}
