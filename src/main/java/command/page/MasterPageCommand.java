package command.page;

import bundle.ConfigurationManager;
import command.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MasterPageCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    final HttpSession session = request.getSession();
    final int masterId = (Integer)session.getAttribute("iduser");
    return ConfigurationManager.getProperty("path.page.master");
  }
}
