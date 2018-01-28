package filter;


import command.CommandEnum;
import command.Redirector;
import entity.Role;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Special filter that controls what commands allowed to be executed by specific user depending on
 * it's role
 */
@WebFilter(urlPatterns = {"/controller"}, servletNames = {"Controller"})
public class SecurityFilter implements Filter {

  private Map<Role, List<CommandEnum>> filterCommands;
  private static final String ATTRIBUTE_NAME_ROLE = "role";
  private static final String PARAM_NAME_COMMAND = "command";

  /**
   * Innits Roles with allowed comamnds
   * @param filterConfig
   * @throws ServletException
   */
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    final CommandEnum[] adminCommands = {CommandEnum.ADMINISTRATORPAGE, CommandEnum.ADDING_FORM,
        CommandEnum.CREATE_VISIT, CommandEnum.FINISH_VISIT, CommandEnum.DELETE_VISIT,
        CommandEnum.LOGOUT, CommandEnum.SETLOCALE};
    final CommandEnum[] masterCommands = {CommandEnum.MASTERPAGE, CommandEnum.FINISH_VISIT,
        CommandEnum.LOGOUT, CommandEnum.SETLOCALE};
    final CommandEnum[] customerCommands = {CommandEnum.CUSTOMERPAGE, CommandEnum.SEARCHING_FORM,
        CommandEnum.RESERVE_VISIT, CommandEnum.LOGOUT, CommandEnum.REVIEW_FORM,
        CommandEnum.WRITE_REVIEW, CommandEnum.SEARCH_VISIT, CommandEnum.SETLOCALE};
    final CommandEnum[] guestCommands = {CommandEnum.LOGIN, CommandEnum.SETLOCALE,
        CommandEnum.REGISTRATIONFORM, CommandEnum.SIGN_UP};
    filterCommands = new HashMap<>();
    filterCommands.put(Role.ADMINISTRATOR, new ArrayList<>(Arrays.asList(adminCommands)));
    filterCommands.put(Role.MASTER, new ArrayList<>(Arrays.asList(masterCommands)));
    filterCommands.put(Role.CUSTOMER, new ArrayList<>(Arrays.asList(customerCommands)));
    filterCommands.put(Role.UNKNOWN, new ArrayList<>(Arrays.asList(guestCommands)));

  }

  /**
   * Redirects if command is unavailable
   * @param request
   * @param response
   * @param filterChain
   * @throws IOException
   * @throws ServletException
   */
  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain filterChain) throws IOException, ServletException {
    final HttpServletRequest req = (HttpServletRequest) request;
    final HttpServletResponse resp = (HttpServletResponse) response;

    final HttpSession session = req.getSession();
    Role role = (Role) session.getAttribute(ATTRIBUTE_NAME_ROLE);
    if (role == null) {
      role = Role.UNKNOWN;
    }
    final String action = req.getParameter(PARAM_NAME_COMMAND);
    CommandEnum command = CommandEnum.valueOf(action.toUpperCase());
    if (filterCommands.get(role).contains(command)) {
      filterChain.doFilter(request, response);
    } else {
      req.getRequestDispatcher(Redirector.getMenu(role, req)).forward(request, response);
    }

  }

  @Override
  public void destroy() {

  }
}


