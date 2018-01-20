package command.action;

import bundle.ConfigurationManager;
import command.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SetLocaleCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    final HttpSession session = request.getSession();

    final Logger logger = LogManager.getRootLogger();

    logger.info(request.getParameter("language"));

    if (request.getParameter("language").equals("ua")){
      session.setAttribute("locale","uk_UA");
    } else {
      session.setAttribute("locale","us_US");
    }
    return ConfigurationManager.getProperty("path.page.login");
  }
}
