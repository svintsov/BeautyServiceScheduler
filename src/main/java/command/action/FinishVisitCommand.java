package command.action;

import bundle.MessageManager;
import command.Command;
import command.Redirector;
import entity.Role;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import service.VisitService;

public class FinishVisitCommand implements Command {

  private static final String PARAM_NAME_ID_VISIT = "idvisit";
  private static final String ATTRIBUTE_NAME_ROLE ="role";

  @Override
  public String execute(final HttpServletRequest request) {
    final VisitService visitService = new VisitService();
    try {
      visitService.finishVisitByID(Integer.valueOf(request.getParameter(PARAM_NAME_ID_VISIT)));
      request.setAttribute("errorMessage","");
    } catch (SQLException e) {
      request.setAttribute("errorMessage", MessageManager.getProperty(e.getMessage()));
    }
    return Redirector.getMenu((Role)request.getSession().getAttribute(ATTRIBUTE_NAME_ROLE),request);
  }
}
