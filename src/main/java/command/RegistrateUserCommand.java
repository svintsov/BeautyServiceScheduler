package command;

import dao.UserDao;
import entity.Role;
import entity.User;
import java.util.concurrent.atomic.AtomicReference;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrateUserCommand implements Command {

  @Override
  public String execute(HttpServletRequest request,HttpServletResponse response) {
    final String login = request.getParameter("login");
    final String password = request.getParameter("password");
    final String email = request.getParameter("email");
    final String full_name = request.getParameter("full name");

    final AtomicReference<UserDao> dao = (AtomicReference<UserDao>) request
        .getServletContext().getAttribute("dao");

    if(!dao.get().userIsExist(login,password)){
      dao.get().add(new User(4,login,password,email, Role.USER));
      request.getServletContext().setAttribute("dao",dao);
    }
      return request.getContextPath() + "/api/login";
  }
}
