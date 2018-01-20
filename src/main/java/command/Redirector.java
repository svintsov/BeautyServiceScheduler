package command;

import bundle.ConfigurationManager;
import entity.Role;
import javax.servlet.http.HttpServletRequest;

public class Redirector {
  public static String getMenu(final Role role, final HttpServletRequest request) {

    if (role.equals(Role.ADMINISTRATOR)) {
      CommandEnum commandEnum = CommandEnum.ADMINPAGE;
      return commandEnum.getCurrentCommand().execute(request);

    } else if (role.equals(Role.CUSTOMER)) {
      return ConfigurationManager.getProperty("path.page.main");

    } else if (role.equals(Role.MASTER)) {
      CommandEnum commandEnum = CommandEnum.MASTERPAGE;
      return commandEnum.getCurrentCommand().execute(request);

    } else {
      return ConfigurationManager.getProperty("path.page.index");
    }
  }
}
