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
    String page;
// извлечение из запроса логина и пароля
    String login = request.getParameter(PARAM_NAME_LOGIN);
    String password = request.getParameter(PARAM_NAME_PASSWORD);

    final LoginService loginService = new LoginService();

    final HttpSession session = request.getSession();

    //Logged user.
    if (nonNull(session) &&
        nonNull(session.getAttribute("login")) &&
        nonNull(session.getAttribute("password"))) {

      final Role role = (Role) session.getAttribute("role");

      return getMenu(role);

    } else {

      final Role role;
      try {
        role = loginService.getRole(login, password);
        request.getSession().setAttribute("password", password);
        request.getSession().setAttribute("login", login);
        request.getSession().setAttribute("role", role);
        return getMenu(role);
      } catch (SQLException e) {
        request.setAttribute("errorLoginPassMessage",
            MessageManager.getProperty("message.loginerror"));
        page = ConfigurationManager.getProperty("path.page.login");
        return page;
      }

    }

  }

  /**
   * Move user to menu. If access 'admin' move to admin menu. If access 'user' move to user menu.
   */
  private String getMenu(
      final Role role) {

    if (role.equals(Role.ADMINISTRATOR)) {
      return ConfigurationManager.getProperty("path.page.admin");


    } else if (role.equals(Role.CUSTOMER)) {
      return ConfigurationManager.getProperty("path.page.main");

    } else if (role.equals(Role.MASTER)) {
      return ConfigurationManager.getProperty("path.page.master");

    } else {
      return ConfigurationManager.getProperty("path.page.index");
    }
  }
}
