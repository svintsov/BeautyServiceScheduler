package servlet;

import command.Command;
import command.GoToLoginPageCommand;
import command.GoToRegistrationPageCommand;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

  private Map<String, Command> commands;

  /**
   * just testing servlet context listener with some data
   * @param servletContextEvent
   */
  @Override
  public void contextInitialized(ServletContextEvent servletContextEvent) {

    final ServletContext servletContext =
        servletContextEvent.getServletContext();

    commands = new ConcurrentHashMap<>();

    commands.put("login", new GoToLoginPageCommand());
    commands.put("registration",new GoToRegistrationPageCommand());

    servletContext.setAttribute("commands", commands);

  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    //Close recourse.
    commands = null;
  }
}