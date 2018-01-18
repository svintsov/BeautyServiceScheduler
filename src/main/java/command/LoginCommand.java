package command;

import static java.util.Objects.nonNull;

import bundle.ConfigurationManager;
import bundle.MessageManager;
import entity.Role;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import service.LoginService;

public class LoginCommand implements Command {

  private static final String PARAM_NAME_LOGIN = "login";
  private static final String PARAM_NAME_PASSWORD = "password";

  @Override
  public String execute(HttpServletRequest request) {
    // извлечение из запроса логина и пароля
    String login = request.getParameter(PARAM_NAME_LOGIN);
    String password = request.getParameter(PARAM_NAME_PASSWORD);

    HttpSession session = request.getSession();

    final LoginService loginService = new LoginService();
    //Logged user.
    if (nonNull(session) &&
        nonNull(session.getAttribute("login")) &&
        nonNull(session.getAttribute("password"))) {

      return Redirector.getMenu((Role) session.getAttribute("role"), request);

    } else {
      try {

        final Role role = loginService.getRole(login, password);
        request.getSession().setAttribute("password", password);
        request.getSession().setAttribute("login", login);
        request.getSession().setAttribute("role", role);
        return Redirector.getMenu(role, request);
      } catch (SQLException e) {
        request.setAttribute("errorLoginPassMessage",
            MessageManager.getProperty("message.loginerror"));
        return ConfigurationManager.getProperty("path.page.login");
      }
    }
  }

  private String redirectUser(final HttpServletRequest request, final HttpSession session,
      final String login, final String password) {
      return null;
  }


}
