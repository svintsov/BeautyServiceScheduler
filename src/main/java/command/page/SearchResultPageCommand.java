package command.page;

import bundle.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

public class SearchResultPageCommand implements command.Command {

  @Override
  public String execute(HttpServletRequest request) {
    return ConfigurationManager.getProperty("path.page.search.result");
  }
}
