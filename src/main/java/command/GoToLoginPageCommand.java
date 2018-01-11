package command;

import javax.servlet.http.HttpServletRequest;

public class GoToLoginPageCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    return "/login.jsp";
  }
}
