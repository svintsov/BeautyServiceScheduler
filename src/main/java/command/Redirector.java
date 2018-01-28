package command;

import bundle.ConfigurationManager;
import entity.Role;
import javax.servlet.http.HttpServletRequest;

/**
 * Special class for redirection to main role's pages
 */
public class Redirector {

  private static final String COMMAND_POSTFIX = "PAGE";

  public static String getMenu(final Role role, final HttpServletRequest request) {

    if (role != null && !role.equals(Role.UNKNOWN)) {
      return CommandEnum.valueOf(role.toString().concat(COMMAND_POSTFIX)).getCurrentCommand()
          .execute(request);
    } else {
      return ConfigurationManager.getProperty("path.page.index");
    }
  }
}
