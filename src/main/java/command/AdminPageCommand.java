package command;

import bundle.ConfigurationManager;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import service.VisitService;

public class AdminPageCommand implements Command {

  final private VisitService visitService = new VisitService();

  @Override
  public String execute(HttpServletRequest request) {
    try {
      request.setAttribute("visits",visitService.getAllVisits());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return ConfigurationManager.getProperty("path.page.admin");
  }
}
