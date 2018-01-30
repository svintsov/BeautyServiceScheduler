package command.action;

import bundle.ConfigurationManager;
import bundle.MessageManager;
import command.Command;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import service.SignUpService;

/**
 * Registration command
 */
public class SignUpCommand implements Command {

  private static final String PARAM_NAME_LOGIN = "login";
  private static final String PARAM_NAME_PASSWORD = "password";
  private static final String PARAM_NAME_EMAIL = "email";
  private static final String PARAM_NAME_FULL_NAME = "full name";

  /**
   * Executes command
   * @param request
   * @return Redirection to role's main page
   */
  @Override
  public String execute(final HttpServletRequest request) {

    final String login = request.getParameter(PARAM_NAME_LOGIN);
    final String password = request.getParameter(PARAM_NAME_PASSWORD);
    final String email = request.getParameter(PARAM_NAME_EMAIL);
    final String fullName = request.getParameter(PARAM_NAME_FULL_NAME);

    final SignUpService signUpService = new SignUpService();

    try {
      signUpService.register(login,password,email,fullName);
      return ConfigurationManager.getProperty("path.page.login");
    } catch (SQLException e) {
      request.setAttribute("errorRegistrationMessage",
          MessageManager.getProperty("message.registrationerror"));
      return ConfigurationManager.getProperty("path.page.registration");
    }
  }
}
