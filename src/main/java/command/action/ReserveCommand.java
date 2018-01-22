package command.action;

import bundle.MessageManager;
import command.Redirector;
import entity.Role;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import service.VisitService;

public class ReserveCommand implements command.Command {

  @Override
  public String execute(HttpServletRequest request) {
    final VisitService visitService = new VisitService();
    try {
      visitService.reserveVisitByID(Integer.valueOf(request.getParameter("idvisit")),
          (Integer) request.getSession().getAttribute("iduser"));
      request.setAttribute("errorMessage", "");
    } catch (SQLException e) {
      request.setAttribute("errorMessage", MessageManager.getProperty(e.getMessage()));
    }
    return Redirector.getMenu((Role) request.getSession().getAttribute("role"), request);
  }
}

