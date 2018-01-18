package command;

import entity.Role;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import service.VisitService;

public class FinishVisitCommand implements Command {

  private  final VisitService visitService = new VisitService();

  @Override
  public String execute(HttpServletRequest request) {

    try {
      visitService.updateStateByID(Integer.valueOf(request.getParameter("idvisit")));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Redirector.getMenu((Role)request.getSession().getAttribute("role"),request);
  }
}
