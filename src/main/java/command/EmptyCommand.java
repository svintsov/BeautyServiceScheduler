package command;

import bundle.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

/**
 * Empty command for special needs as mock
 */
public class EmptyCommand implements Command {

  @Override
  public String execute(final HttpServletRequest request) {
    return ConfigurationManager.getProperty("path.page.login");
  }
}

