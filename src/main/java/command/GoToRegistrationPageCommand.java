package command;

import javax.servlet.http.HttpServletRequest;

public class GoToRegistrationPageCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    return "/registration.jsp";
  }
}
