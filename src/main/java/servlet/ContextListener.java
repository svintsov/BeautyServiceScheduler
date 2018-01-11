package servlet;

import command.Command;
import command.GoToLoginPageCommand;
import command.GoToRegistrationPageCommand;
import command.LogoutCommand;
import dao.UserDao;
import entity.Role;
import entity.User;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

  private Map<String, Command> commands;
  private AtomicReference<UserDao> dao;

  /**
   * just testing servlet context listener with some data
   * @param servletContextEvent
   */
  @Override
  public void contextInitialized(ServletContextEvent servletContextEvent) {

    final ServletContext servletContext =
        servletContextEvent.getServletContext();

    commands = new ConcurrentHashMap<>();
    dao = new AtomicReference<>(new UserDao());

    dao.get().add(new User(1, "Kyrylo", "1", "email",Role.USER));
    dao.get().add(new User(2, "Admin", "1", "email", Role.ADMINISTRATOR));
    dao.get().add(new User(3, "Master", "1", "email", Role.MASTER));

    commands.put("login", new GoToLoginPageCommand());
    commands.put("registration",new GoToRegistrationPageCommand());
    commands.put("logout",new LogoutCommand());

    servletContext.setAttribute("commands", commands);
    servletContext.setAttribute("dao",dao);

  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    //Close recourse.
    commands = null;
    dao=null;
  }
}