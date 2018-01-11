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

  private Map<String, Command> commands;

  public void init() {
    final Object commands = getServletContext().getAttribute("commands");

    if (commands == null || !(commands instanceof ConcurrentHashMap)) {

      throw new IllegalStateException("Commands not found!");
    } else {

      this.commands = (ConcurrentHashMap<String, Command>) commands;
    }

  }

  public void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws IOException, ServletException {

    String path = request.getRequestURI();
    path = path.replaceAll(".*/api/", "");
    Command command = commands.getOrDefault(path, (r) -> "/index.jsp)");
    String page = command.execute(request);
    request.getRequestDispatcher(page).forward(request, response);
    //  response.getWriter().print("Hello from servlet");
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    /*List<Student> students = studentService.getAllStudents();
    request.setAttribute("students" , students);
    request.getRequestDispatcher("./WEB-INF/studentlist.jsp")
        .forward(request,response);*/
  }

}
