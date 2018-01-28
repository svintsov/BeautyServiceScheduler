package command.page;

import bundle.ConfigurationManager;
import command.Command;
import entity.Role;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import service.GetUsersForInputService;

/**
 * Command redirects to adding form
 */
public class AddingFormCommand implements Command {

  final String ATTRIBUTE_NAME_MASTERS = "masters";
  final String ATTRIBUTE_NAME_CUSTOMERS = "customers";

  @Override
  public String execute(final HttpServletRequest request) {
    final GetUsersForInputService service = new GetUsersForInputService();

    try {

      request.setAttribute(ATTRIBUTE_NAME_MASTERS,service.getAllUsers(Role.MASTER));
      request.setAttribute(ATTRIBUTE_NAME_CUSTOMERS,service.getAllUsers(Role.CUSTOMER));

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return ConfigurationManager.getProperty("path.page.adding");
  }
}
