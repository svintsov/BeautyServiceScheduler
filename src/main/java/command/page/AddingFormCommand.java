package command.page;

import bundle.ConfigurationManager;
import command.Command;
import entity.Role;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import service.GetUsersForInputService;

public class AddingFormCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    GetUsersForInputService service = new GetUsersForInputService();

    try {
      request.setAttribute("masters",service.getAllUsers(Role.MASTER));
      request.setAttribute("customers",service.getAllUsers(Role.CUSTOMER));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return ConfigurationManager.getProperty("path.page.adding");
  }
}
