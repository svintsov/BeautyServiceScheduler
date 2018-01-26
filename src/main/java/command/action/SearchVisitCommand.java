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
  private static final String ATTRIBUTE_NAME_VISITS = "visits";

  @Override
  public String execute(final HttpServletRequest request) {

    final VisitService visitService = new VisitService();

    final String date = request.getParameter(PARAM_NAME_DAY);
    final String type = request.getParameter(PARAM_NAME_SERVICE);

    try {

      final List<Visit> visits = visitService.getAllVisitsForDate(type, date);
      request.setAttribute(ATTRIBUTE_NAME_VISITS, visits);

    } catch (IOException e) {
      request.setAttribute("errorSearchVisitMessage",
          MessageManager.getProperty("message.visit.invalid_input.date"));
    } catch (SQLException e) {
      request.setAttribute("errorSearchVisitMessage",
          MessageManager.getProperty("message.visit.search_error"));
    }
    return CommandEnum.SEARCHING_FORM.getCurrentCommand().execute(request);
  }
}
