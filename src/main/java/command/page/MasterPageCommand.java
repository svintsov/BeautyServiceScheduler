package command.page;

import bundle.ConfigurationManager;
import command.Command;
import entity.Role;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import service.VisitService;

/**
 * Command redirects to master's main page
 */
public class MasterPageCommand implements Command {

  private static final String ATTRIBUTE_NAME_IDUSER="iduser";
  private static final String ATTRIBUTE_NAME_VISITS="visits";

  @Override
  public String execute(final HttpServletRequest request) {
    final VisitService visitService= new VisitService();
    final HttpSession session = request.getSession();
    final int masterId = (Integer)session.getAttribute(ATTRIBUTE_NAME_IDUSER);

    try {

      request.setAttribute(ATTRIBUTE_NAME_VISITS,visitService.getAllVisitsForUser(masterId, Role.MASTER));

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return ConfigurationManager.getProperty("path.page.main");
  }
}
