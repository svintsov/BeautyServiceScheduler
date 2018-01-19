package command.page;

import bundle.ConfigurationManager;
import command.Command;
import javax.servlet.http.HttpServletRequest;

public class AddingFormCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    return ConfigurationManager.getProperty("path.page.adding");
  }
}
