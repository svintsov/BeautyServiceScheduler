package command.action;

import bundle.MessageManager;
import command.Command;
import command.Redirector;
import entity.Role;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import service.VisitService;

public class FinishVisitCommand implements Command {

  @Override
  public String execute(final HttpServletRequest request) {
    final VisitService visitService = new VisitService();
    try {
      visitService.updateStateByID(Integer.valueOf(request.getParameter("idvisit")));
      request.setAttribute("errorMessage","");
    } catch (SQLException e) {
      request.setAttribute("errorMessage", MessageManager.getProperty(e.getMessage()));
    }
    return Redirector.getMenu((Role)request.getSession().getAttribute("role"),request);
  }
}
