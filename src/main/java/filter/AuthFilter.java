package filter;

import static java.util.Objects.nonNull;

import dao.UserDao;
import entity.Role;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(final ServletRequest request,
      final ServletResponse response,
      final FilterChain filterChain)

      throws IOException, ServletException {

    final HttpServletRequest req = (HttpServletRequest) request;
    final HttpServletResponse res = (HttpServletResponse) response;

    final String login = req.getParameter("login");
    final String password = req.getParameter("password");

    @SuppressWarnings("unchecked") final AtomicReference<UserDao> dao = (AtomicReference<UserDao>) req
        .getServletContext().getAttribute("dao");

    final HttpSession session = req.getSession();

    //Logged user.
    if (nonNull(session) &&
        nonNull(session.getAttribute("login")) &&
        nonNull(session.getAttribute("password"))) {

      final Role role = (Role) session.getAttribute("role");

      moveToMenu(req, res, role);


    } else if (dao.get().userIsExist(login, password)) {

      final Role role = dao.get().getRoleByLoginPassword(login, password);

      req.getSession().setAttribute("password", password);
      req.getSession().setAttribute("login", login);
      req.getSession().setAttribute("role", role);

      moveToMenu(req, res, role);

    } else {

      moveToMenu(req, res, Role.UNKNOWN);
    }
  }

  /**
   * Move user to menu. If access 'admin' move to admin menu. If access 'user' move to user menu.
   */
  private void moveToMenu(final HttpServletRequest req,
      final HttpServletResponse res,
      final Role role)
      throws ServletException, IOException {

    if (role.equals(Role.ADMINISTRATOR)) {

      req.getRequestDispatcher("/admin_menu.jsp").forward(req, res);

    } else if (role.equals(Role.USER)) {

      req.getRequestDispatcher("/user-menu.jsp").forward(req, res);

    } else if (role.equals(Role.MASTER)) {

      req.getRequestDispatcher("/master-menu.jsp").forward(req, res);

    } else {

      req.getRequestDispatcher("/login.jsp").forward(req, res);
    }
  }


  @Override
  public void destroy() {
  }

}