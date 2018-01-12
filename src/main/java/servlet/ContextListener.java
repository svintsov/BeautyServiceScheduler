package servlet;

import command.Command;
import dao.UserDao;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

  private Map<String, Command> commandsOfGetMethods;
  private Map<String, Command> commandsOfPostMethods;
  private AtomicReference<UserDao> dao;

  /**
   * just testing servlet context listener with some data
   * @param servletContextEvent
   */
  @Override
  public void contextInitialized(ServletContextEvent servletContextEvent) {

    /*final ServletContext servletContext =
        servletContextEvent.getServletContext();

    commandsOfGetMethods = new ConcurrentHashMap<>();
    commandsOfPostMethods = new ConcurrentHashMap<>();
    dao = new AtomicReference<>(new UserDao());

    dao.get().add(new User(1, "Kyrylo", "1", "email",Role.USER));
    dao.get().add(new User(2, "Admin", "1", "email", Role.ADMINISTRATOR));
    dao.get().add(new User(3, "Master", "1", "email", Role.MASTER));

    commandsOfGetMethods.put("login", new GoToLoginPageCommand());
    commandsOfGetMethods.put("registration",new GoToRegistrationPageCommand());
    commandsOfGetMethods.put("logout",new LogoutCommand());
    commandsOfPostMethods.put("registration",new RegistrateUserCommand());

    servletContext.setAttribute("commandsOfGetMethods", commandsOfGetMethods);
    servletContext.setAttribute("commandsOfPostMethods", commandsOfPostMethods);
    servletContext.setAttribute("dao",dao);
    */
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    //Close recourse.
    /*
    commandsOfGetMethods = null;
    commandsOfPostMethods=null;
    dao=null;*/
  }
}