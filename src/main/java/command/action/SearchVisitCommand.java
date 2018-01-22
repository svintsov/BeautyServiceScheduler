package command.action;

import bundle.MessageManager;
import command.Command;
import command.CommandEnum;
import entity.Visit;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import service.VisitService;

public class SearchVisitCommand implements Command {

  private static final String PARAM_NAME_SERVICE = "services_select";
  private static final String PARAM_NAME_DAY = "day";

  private final VisitService visitService = new VisitService();

  @Override
  public String execute(HttpServletRequest request) {

    final String date =request.getParameter(PARAM_NAME_DAY);
    final String type = request.getParameter(PARAM_NAME_SERVICE);

    try {
      List<Visit> visits = visitService.getAllVisitsForDate(type,date);
      request.setAttribute("visits",visits);
    } catch (IOException e) {
      request.setAttribute("errorSearchVisitMessage",
          MessageManager.getProperty("message.visit.invalid_input.date"));
      return CommandEnum.SEARCHING_FORM.getCurrentCommand().execute(request);
    } catch (SQLException e) {
      request.setAttribute("errorSearchVisitMessage",
          MessageManager.getProperty("message.visit.search_error"));
      return CommandEnum.SEARCHING_FORM.getCurrentCommand().execute(request);
    }
    return CommandEnum.SEARCH_RESULT_PAGE.getCurrentCommand().execute(request);
  }
}
