package command;

import bundle.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements Command {

  @Override
  public String execute(final HttpServletRequest request) {
    return ConfigurationManager.getProperty("path.page.login");
  }
}

