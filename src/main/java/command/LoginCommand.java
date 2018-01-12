package command;

import static java.util.Objects.nonNull;

import bundle.ConfigurationManager;
import bundle.MessageManager;
import dao.UserDao;
import entity.Role;
import java.util.concurrent.atomic.AtomicReference;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import service.LoginService;

public class LoginCommand implements Command {

  private static final String PARAM_NAME_LOGIN = "login";
  private static final String PARAM_NAME_PASSWORD = "password";

  @Override
  public String execute(HttpServletRequest request) {
    String page = null;
// извлечение из запроса логина и пароля
    String login = request.getParameter(PARAM_NAME_LOGIN);
    String password = request.getParameter(PARAM_NAME_PASSWORD);
// проверка логина и пароля

    final AtomicReference<UserDao> dao = (AtomicReference<UserDao>) request
        .getServletContext().getAttribute("dao");

    final HttpSession session = request.getSession();

    //Logged user.
    if (nonNull(session) &&
        nonNull(session.getAttribute("login")) &&
        nonNull(session.getAttribute("password"))) {

      final Role role = (Role) session.getAttribute("role");

      return getMenu(role);


    } else if (LoginService.checkLogin(login,password,dao)) {

      final Role role = LoginService.getRole(login,password,dao);

      request.getSession().setAttribute("password", password);
      request.getSession().setAttribute("login", login);
      request.getSession().setAttribute("role", role);

      return getMenu(role);

    }
    else {
      request.setAttribute("errorLoginPassMessage",
          MessageManager.getProperty("message.loginerror"));
      page = ConfigurationManager.getProperty("path.page.login");
    }
    return page;
  }

  /**
   * Move user to menu. If access 'admin' move to admin menu. If access 'user' move to user menu.
   */
  private String getMenu(
      final Role role) {

    if (role.equals(Role.ADMINISTRATOR)) {
      return ConfigurationManager.getProperty("path.page.admin");


    } else if (role.equals(Role.USER)) {
      return ConfigurationManager.getProperty("path.page.main");

    } else if (role.equals(Role.MASTER)) {
      return ConfigurationManager.getProperty("path.page.master");

    } else {
      return ConfigurationManager.getProperty("path.page.index");
    }
  }
}
