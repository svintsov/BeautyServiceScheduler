package command.action;

import static java.util.Objects.nonNull;

import bundle.ConfigurationManager;
import bundle.MessageManager;
import command.Command;
import command.Redirector;
import entity.Role;
import entity.User;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import service.LoginService;

public class LoginCommand implements Command {

  private static final String PARAM_NAME_LOGIN = "login";
  private static final String PARAM_NAME_PASSWORD = "password";
  private static final String ATTRIBUTE_NAME_ROLE = "role";
  private static final String ATTRIBUTE_NAME_ID_USER = "iduser";

  /**
   * Executes command
   * @param request
   * @return Redirection to role's main page or login page
   */
  @Override
  public String execute(final HttpServletRequest request) {

    final String login = request.getParameter(PARAM_NAME_LOGIN);
    final String password = request.getParameter(PARAM_NAME_PASSWORD);

    final HttpSession session = request.getSession();
    //Logged user.
    if (nonNull(session) &&
        nonNull(session.getAttribute(PARAM_NAME_LOGIN)) &&
        nonNull(session.getAttribute(PARAM_NAME_PASSWORD))) {

      return Redirector.getMenu((Role) session.getAttribute(ATTRIBUTE_NAME_ROLE), request);
    }
    return getRedirect(request, session, login, password);

  }

  private String getRedirect(final HttpServletRequest request, final HttpSession session,
      final String login, final String password) {
    try {

      final LoginService loginService = new LoginService();
      final User user = loginService.getUserByLogin(login, password);
      final Role role = user.getRole();

      session.setAttribute(PARAM_NAME_PASSWORD, password);
      session.setAttribute(PARAM_NAME_LOGIN, login);
      session.setAttribute(ATTRIBUTE_NAME_ROLE, role);
      session.setAttribute(ATTRIBUTE_NAME_ID_USER, user.getId());

      return Redirector.getMenu(role, request);
    } catch (SQLException e) {
      request.setAttribute("errorLoginPassMessage",
          MessageManager.getProperty("message.loginerror"));
      return ConfigurationManager.getProperty("path.page.login");
    }
  }

}
