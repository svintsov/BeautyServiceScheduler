package command.action;

import bundle.MessageManager;
import command.Command;
import command.CommandEnum;
import command.Redirector;
import entity.Role;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import service.VisitService;

/**
 * Command for creating visit from admin role
 */
public class CreateVisitCommand implements Command {

  final String PARAM_NAME_SERVICE = "services_select";
  final String PARAM_NAME_DAY = "day";
  final String PARAM_NAME_HOUR = "hour";
  final String PARAM_NAME_CUSTOMER = "customer";
  final String PARAM_NAME_MASTER = "master";
  final String PARAM_NAME_STATE = "states_select";
  private static final String ATTRIBUTE_NAME_ROLE="role";

  /**
   * Executes command
   * @param request
   * @return Redirection to role's main page
   */
  @Override
  public String execute(final HttpServletRequest request) {

    final VisitService visitService = new VisitService();

    try {
      visitService.createVisit(retrieveVisit(request));
    } catch (SQLException e) {
      request.setAttribute("errorCreateVisitMessage",
          MessageManager.getProperty("message.visit.creation_error"));
      return CommandEnum.ADDING_FORM.getCurrentCommand().execute(request);
    } catch (IOException e) {
      request.setAttribute("errorCreateVisitMessage",
          MessageManager.getProperty("message.visit.invalid_input.date"));
      return CommandEnum.ADDING_FORM.getCurrentCommand().execute(request);
    }

    return Redirector.getMenu((Role)request.getSession().getAttribute(ATTRIBUTE_NAME_ROLE),request);
  }

  /**
   * Retrieves params from the user's input
   * @param request
   * @return map of inputs
   */
  private Map<String,String> retrieveVisit(HttpServletRequest request){

    final Map<String, String> map = new HashMap<>();

    map.put(PARAM_NAME_SERVICE,request.getParameter(PARAM_NAME_SERVICE));
    map.put(PARAM_NAME_DAY,request.getParameter(PARAM_NAME_DAY));
    map.put(PARAM_NAME_HOUR,request.getParameter(PARAM_NAME_HOUR));
    map.put(PARAM_NAME_CUSTOMER,request.getParameter(PARAM_NAME_CUSTOMER));
    map.put(PARAM_NAME_MASTER,request.getParameter(PARAM_NAME_MASTER));
    map.put(PARAM_NAME_STATE,request.getParameter(PARAM_NAME_STATE));

    return map;
  }
}
