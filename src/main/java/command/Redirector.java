package command;

import bundle.ConfigurationManager;
import entity.Role;
import javax.servlet.http.HttpServletRequest;

public class Redirector {

  private static final String COMMAND_POSTFIX = "PAGE";

  public static String getMenu(final Role role, final HttpServletRequest request) {

    if (role != null) {
      return CommandEnum.valueOf(role.toString().concat(COMMAND_POSTFIX)).getCurrentCommand()
          .execute(request);
    } else {
      return ConfigurationManager.getProperty("path.page.index");
    }
  }
}
