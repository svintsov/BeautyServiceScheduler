package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoToRegistrationPageCommand implements Command {

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    return "/registration.jsp";
  }
}
