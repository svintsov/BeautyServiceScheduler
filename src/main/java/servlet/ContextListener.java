package servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class ContextListener implements ServletContextListener {

  //private AtomicReference<UserDao> dao;

  /**
   * just testing servlet context listener with some data
   * @param servletContextEvent
   */
  @Override
  public void contextInitialized(ServletContextEvent servletContextEvent) {

    final ServletContext servletContext =
        servletContextEvent.getServletContext();

    final Logger logger = LogManager.getRootLogger();

    logger.info("first log from context listener");

    //dao = new AtomicReference<>(DaoFactory.getInstance().createUserDao());

    /*dao.get().add(new User(1, "Kyrylo", "1", "email", Role.USER));
    dao.get().add(new User(2, "Admin", "1", "email", Role.ADMINISTRATOR));
    dao.get().add(new User(3, "Master", "1", "email", Role.MASTER));*/

    //servletContext.setAttribute("dao",dao);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    //dao.get().close();
    //dao=null;
  }
}