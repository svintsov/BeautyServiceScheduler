package command;

import entity.Role;
import javax.servlet.http.HttpServletRequest;

public class CreateVisitCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {


    return Redirector.getMenu((Role)request.getSession().getAttribute("role"),request);
  }
}
