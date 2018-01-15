package command;

import javax.servlet.http.HttpServletRequest;

public class SignUpCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    return null;
  }

 /* private static final String PARAM_NAME_LOGIN = "login";
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


    if (!signUpService.isExist(login, email)) {
      signUpService.registerUser(login, password, email, fullName);
      //request.getServletContext().setAttribute("dao", dao);
      return ConfigurationManager.getProperty("path.page.login");
    } else {
      request.setAttribute("errorRegistrationMessage",
          MessageManager.getProperty("message.registrationerror"));
      return ConfigurationManager.getProperty("path.page.registration");
    }

  }*/
}
