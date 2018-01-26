package command.action;

import bundle.MessageManager;
import command.Redirector;
import entity.Role;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import service.VisitService;

public class ReserveCommand implements command.Command {

  private static final String ATTRIBUTE_NAME_ID_USER = "iduser";
  private static final String ATTRIBUTE_NAME_ROLE = "role";
  private static final String PARAM_NAME_ID_VISIT = "idvisit";

  @Override
  public String execute(final HttpServletRequest request) {
    final VisitService visitService = new VisitService();
    try {

      visitService.reserveVisitByID(Integer.valueOf(request.getParameter(PARAM_NAME_ID_VISIT)),
          (Integer) request.getSession().getAttribute(ATTRIBUTE_NAME_ID_USER));

      request.setAttribute("errorMessage", "");
    } catch (SQLException e) {
      request.setAttribute("errorMessage", MessageManager.getProperty(e.getMessage()));
    }
    return Redirector.getMenu((Role) request.getSession().getAttribute(ATTRIBUTE_NAME_ROLE), request);
  }
}

