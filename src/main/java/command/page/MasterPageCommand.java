package command.page;

import bundle.ConfigurationManager;
import command.Command;
import entity.Role;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import service.VisitService;

public class MasterPageCommand implements Command {

  private final VisitService visitService= new VisitService();

  @Override
  public String execute(HttpServletRequest request) {
    final HttpSession session = request.getSession();
    final int masterId = (Integer)session.getAttribute("iduser");
    try {
      request.setAttribute("visits",visitService.getAllVisitsForUser(masterId, Role.MASTER));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return ConfigurationManager.getProperty("path.page.master");
  }
}
