package command.page;

import bundle.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

/**
 * Command redirects to searching form
 */
public class SearchingFormCommand implements command.Command {

  @Override
  public String execute(final HttpServletRequest request) {
    return ConfigurationManager.getProperty("path.page.searching");
  }
}
