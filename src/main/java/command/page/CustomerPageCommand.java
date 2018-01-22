package command.page;

import bundle.ConfigurationManager;
import entity.Role;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import service.VisitService;

public class CustomerPageCommand implements command.Command {

  private final VisitService visitService= new VisitService();

  @Override
  public String execute(HttpServletRequest request) {
    final HttpSession session = request.getSession();
    final int customerId = (Integer)session.getAttribute("iduser");
    try {
      request.setAttribute("visits",visitService.getAllVisitsForUser(customerId, Role.CUSTOMER));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return ConfigurationManager.getProperty("path.page.main");
  }
}
