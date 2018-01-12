package command;

import bundle.ConfigurationManager;
import bundle.MessageManager;
import javax.servlet.http.HttpServletRequest;
import service.LoginService;

public class LoginCommand implements Command {

  private static final String PARAM_NAME_LOGIN = "login";
  private static final String PARAM_NAME_PASSWORD = "password";

  @Override
  public String execute(HttpServletRequest request) {
    String page = null;
// извлечение из запроса логина и пароля
    String login = request.getParameter(PARAM_NAME_LOGIN);
    String pass = request.getParameter(PARAM_NAME_PASSWORD);
// проверка логина и пароля
    if (LoginService.checkLogin(login, pass)) {
      request.setAttribute("user", login);
// определение пути к main.jsp
      page = ConfigurationManager.getProperty("path.page.main");
    } else {
      request.setAttribute("errorLoginPassMessage",
          MessageManager.getProperty("message.loginerror"));
      page = ConfigurationManager.getProperty("path.page.login");
    }
    return page;
  }
}
