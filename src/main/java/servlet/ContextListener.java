package servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class ContextListener implements ServletContextListener {


  /**
   * Servlet that checks work of logger
   * @param servletContextEvent
   */
  @Override
  public void contextInitialized(ServletContextEvent servletContextEvent) {

    final Logger logger = LogManager.getRootLogger();

    logger.info("first log from context listener");

  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {

  }
}