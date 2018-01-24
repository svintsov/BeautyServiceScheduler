package command.page;

import bundle.ConfigurationManager;
import command.Command;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import service.VisitService;

public class AdminPageCommand implements Command {

  private static final String ATTRIBUTE_NAME_VISITS="visits";

  @Override
  public String execute(final HttpServletRequest request) {
    final VisitService visitService = new VisitService();

    try {

      request.setAttribute(ATTRIBUTE_NAME_VISITS,visitService.getAllVisits());

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return ConfigurationManager.getProperty("path.page.admin");
  }
}
