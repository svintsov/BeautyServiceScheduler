package command;

import bundle.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

public class AdminPageCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    return ConfigurationManager.getProperty("path.page.admin");
  }
}
