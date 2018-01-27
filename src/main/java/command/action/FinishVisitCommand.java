package command.action;

import bundle.MessageManager;
import command.Command;
import command.Redirector;
import entity.Role;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;

import service.MailService;
import service.VisitService;

public class FinishVisitCommand implements Command {

  private static final String PARAM_NAME_ID_VISIT = "idvisit";
  private static final String ATTRIBUTE_NAME_ROLE ="role";
  private static final String PARAM_NAME_ID_CUSTOMER = "idcustomer";

  @Override
  public String execute(final HttpServletRequest request) {
    final VisitService visitService = new VisitService();
    final MailService mailService = new MailService();

    try {

      visitService.finishVisitByID(Integer.valueOf(request.getParameter(PARAM_NAME_ID_VISIT)));
      mailService.sendMailToUser(Integer.valueOf(request.getParameter(PARAM_NAME_ID_CUSTOMER)));
      request.setAttribute("errorMessage","");

    } catch (SQLException e) {
      request.setAttribute("errorMessage", MessageManager.getProperty(e.getMessage()));
    }
    return Redirector.getMenu((Role)request.getSession().getAttribute(ATTRIBUTE_NAME_ROLE),request);
  }
}
