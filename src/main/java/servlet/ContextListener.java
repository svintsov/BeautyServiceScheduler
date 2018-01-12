package servlet;

import dao.UserDao;
import entity.Role;
import entity.User;
import java.util.concurrent.atomic.AtomicReference;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

  private AtomicReference<UserDao> dao;

  /**
   * just testing servlet context listener with some data
   * @param servletContextEvent
   */
  @Override
  public void contextInitialized(ServletContextEvent servletContextEvent) {

    final ServletContext servletContext =
        servletContextEvent.getServletContext();


    dao = new AtomicReference<>(new UserDao());

    dao.get().add(new User(1, "Kyrylo", "1", "email", Role.USER));
    dao.get().add(new User(2, "Admin", "1", "email", Role.ADMINISTRATOR));
    dao.get().add(new User(3, "Master", "1", "email", Role.MASTER));

    servletContext.setAttribute("dao",dao);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    dao=null;
  }
}