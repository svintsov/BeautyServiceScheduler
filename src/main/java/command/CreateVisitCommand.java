package command;

import bundle.MessageManager;
import entity.Role;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import service.VisitService;

public class CreateVisitCommand implements Command {

  private static final String PARAM_NAME_SERVICE = "services_select";
  private static final String PARAM_NAME_DAY = "day";
  public static final String PARAM_NAME_HOUR = "hour";
  public static final String PARAM_NAME_CUSTOMER = "customer";
  public static final String PARAM_NAME_MASTER = "master";
  public static final String PARAM_NAME_STATE = "states_select";

  private final VisitService visitService = new VisitService();

  @Override
  public String execute(HttpServletRequest request) {

    try {
      visitService.createVisit(retrieveVisit(request));
    } catch (SQLException e) {
      request.setAttribute("errorCreateVisitMessage",
          MessageManager.getProperty("message.visitcreationerror"));
    }

    return Redirector.getMenu((Role)request.getSession().getAttribute("role"),request);
  }

  private Map<String,String> retrieveVisit(HttpServletRequest request){

    Map<String, String> map = new HashMap<>();

    map.put(PARAM_NAME_SERVICE,request.getParameter(PARAM_NAME_SERVICE));
    map.put(PARAM_NAME_DAY,request.getParameter(PARAM_NAME_DAY));
    map.put(PARAM_NAME_HOUR,request.getParameter(PARAM_NAME_HOUR));
    map.put(PARAM_NAME_CUSTOMER,request.getParameter(PARAM_NAME_CUSTOMER));
    map.put(PARAM_NAME_MASTER,request.getParameter(PARAM_NAME_MASTER));
    map.put(PARAM_NAME_STATE,request.getParameter(PARAM_NAME_STATE));

    return map;
  }
}
