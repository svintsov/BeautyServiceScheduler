package command.action;

import static java.util.Objects.nonNull;

import bundle.ConfigurationManager;
import bundle.MessageManager;
import com.sun.istack.internal.NotNull;
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

  @Override
  public String execute(final HttpServletRequest request) {

    final String login = request.getParameter(PARAM_NAME_LOGIN);
    final String password = request.getParameter(PARAM_NAME_PASSWORD);

    final HttpSession session = request.getSession();
    //Logged user.
    if (nonNull(session) &&
        nonNull(session.getAttribute("login")) &&
        nonNull(session.getAttribute("password"))) {

      return Redirector.getMenu((Role) session.getAttribute("role"), request);
    }
    return getRedirect(request, session, login, password);

  }

  private String getRedirect(final HttpServletRequest request, @NotNull final HttpSession session,
      final String login, final String password) {
    try {
      final LoginService loginService = new LoginService();
      final User user = loginService.getUserByLogin(login, password);
      final Role role = user.getRole();
      session.setAttribute("password", password);
      session.setAttribute("login", login);
      session.setAttribute("role", role);
      session.setAttribute("iduser", user.getId());
      return Redirector.getMenu(role, request);
    } catch (SQLException e) {
      request.setAttribute("errorLoginPassMessage",
          MessageManager.getProperty("message.loginerror"));
      return ConfigurationManager.getProperty("path.page.login");
    }
  }

}
