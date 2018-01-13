package command;

import bundle.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

public class RegistrationFormCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    return ConfigurationManager.getProperty("path.page.registration");
  }
}
