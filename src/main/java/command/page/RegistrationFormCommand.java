package command.page;

import bundle.ConfigurationManager;
import command.Command;
import javax.servlet.http.HttpServletRequest;

/**
 * Command redirects to registration form
 */
public class RegistrationFormCommand implements Command {

  @Override
  public String execute(final HttpServletRequest request) {
    return ConfigurationManager.getProperty("path.page.registration");
  }
}
