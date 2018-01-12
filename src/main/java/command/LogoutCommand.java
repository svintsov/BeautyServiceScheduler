package command;

import bundle.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    String page = ConfigurationManager.getProperty("path.page.index");
// уничтожение сессии
    request.getSession().invalidate();
    return page;
  }
}
