package command;

import bundle.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

public class MasterPageCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    return ConfigurationManager.getProperty("path.page.master");
  }
}
