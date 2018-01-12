package servlet;

import command.Command;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet {

  private Map<String, Command> commandsOfGetMethods;
  private Map<String, Command> commandsOfPostMethods;

  public void init() {
    final Object commandsOfGetMethodsTMP = getServletContext().getAttribute("commandsOfGetMethods");

    if (commandsOfGetMethodsTMP == null || !(commandsOfGetMethodsTMP instanceof ConcurrentHashMap)) {
      throw new IllegalStateException("Commands not found!");
    } else {
      this.commandsOfGetMethods = (ConcurrentHashMap<String, Command>) commandsOfGetMethodsTMP;
    }
    final Object commands = getServletContext().getAttribute("commandsOfPostMethods");

    if (commands == null || !(commands instanceof ConcurrentHashMap)) {
      throw new IllegalStateException("Commands not found!");
    } else {
      this.commandsOfPostMethods = (ConcurrentHashMap<String, Command>) commands;
    }

  }

  public void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws IOException, ServletException {

    String path = request.getRequestURI();
    path = path.replaceAll(".*/api/", "");
    Command command = commandsOfGetMethods.getOrDefault(path, (r,rs) -> "/index.jsp)");
    String page = command.execute(request,response);
    request.getRequestDispatcher(page).forward(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    String path = request.getRequestURI();
    path = path.replaceAll(".*/api/", "");
    Command command = commandsOfPostMethods.getOrDefault(path,(r,rs) -> "/index.jsp");
    String page = command.execute(request,response);
    response.sendRedirect(page);
    /*List<Student> students = studentService.getAllStudents();
    request.setAttribute("students" , students);
    request.getRequestDispatcher("./WEB-INF/studentlist.jsp")
        .forward(request,response);*/
  }

}
