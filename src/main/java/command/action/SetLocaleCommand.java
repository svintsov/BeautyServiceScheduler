package command.action;

import bundle.ConfigurationManager;
import command.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SetLocaleCommand implements Command {

  private static final String PARAM_NAME_LANGUAGE = "language";
  private static final String ATTRIBUTE_NAME_LOCALE = "locale";
  private static final String LOCALE_UA="uk_UA";
  private static final String LOCALE_US="us_US";
  private static final String LANGUAGE_UA="ua";

  @Override
  public String execute(final HttpServletRequest request) {
    final HttpSession session = request.getSession();

    final Logger logger = LogManager.getRootLogger();

    logger.info(request.getParameter(PARAM_NAME_LANGUAGE));

    if (request.getParameter(PARAM_NAME_LANGUAGE).equals(LANGUAGE_UA)){
      session.setAttribute(ATTRIBUTE_NAME_LOCALE,LOCALE_UA);
    } else {
      session.setAttribute(ATTRIBUTE_NAME_LOCALE,LOCALE_US);
    }
    return ConfigurationManager.getProperty("path.page.login");
  }
}
