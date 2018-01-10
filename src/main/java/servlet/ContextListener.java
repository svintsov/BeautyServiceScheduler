package servlet;

import entity.User;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

  private Map<Integer, User> users;

  /**
   * just testing servlet context listener with some data
   * @param servletContextEvent
   */
  @Override
  public void contextInitialized(ServletContextEvent servletContextEvent) {

    final ServletContext servletContext =
        servletContextEvent.getServletContext();

    users = new ConcurrentHashMap<>();

    servletContext.setAttribute("users", users);

  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    //Close recourse.
    users = null;
  }
}