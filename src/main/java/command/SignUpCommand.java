package command;

import bundle.ConfigurationManager;
import bundle.MessageManager;
import javax.servlet.http.HttpServletRequest;
import service.SignUpService;

public class SignUpCommand implements Command {

  private static final String PARAM_NAME_LOGIN = "login";
  private static final String PARAM_NAME_PASSWORD = "password";
  public static final String PARAM_NAME_EMAIL = "email";
  public static final String PARAM_NAME_FULL_NAME = "full name";

  @Override
  public String execute(HttpServletRequest request) {

    String login = request.getParameter(PARAM_NAME_LOGIN);
    String password = request.getParameter(PARAM_NAME_PASSWORD);
    String email = request.getParameter(PARAM_NAME_EMAIL);
    String fullName = request.getParameter(PARAM_NAME_FULL_NAME);

    SignUpService signUpService = new SignUpService();

    /*final AtomicReference<UserDaoMock> dao = (AtomicReference<UserDaoMock>) request
        .getServletContext().getAttribute("dao");*/

    if (!signUpService.isExist(login, email)) {
      signUpService.registerUser(login, password, email, fullName);
      //request.getServletContext().setAttribute("dao", dao);
      return ConfigurationManager.getProperty("path.page.login");
    } else {
      request.setAttribute("errorRegistrationMessage",
          MessageManager.getProperty("message.registrationerror"));
      return ConfigurationManager.getProperty("path.page.registration");
    }

  }
}
