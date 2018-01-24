package command.action;

import command.Command;
import command.Redirector;
import entity.Role;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import service.VisitService;

public class DeleteVisitCommand implements Command {

  private static final String PARAM_NAME_ID_VISIT ="idvisit";
  private static final String ATTRIBUTE_NAME_ROLE ="role";

  @Override
  public String execute(final HttpServletRequest request) {

    final VisitService visitService = new VisitService();

    try {
      visitService.deleteByID(Integer.valueOf(request.getParameter(PARAM_NAME_ID_VISIT)));
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return Redirector.getMenu((Role)request.getSession().getAttribute(ATTRIBUTE_NAME_ROLE),request);
  }


}
