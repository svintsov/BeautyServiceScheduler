package servlet;

import command.Command;
import command.GoToLoginPageCommand;
import command.GoToRegistrationPageCommand;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet {

  private Map<String, Command> commands = new HashMap<>();

  public void init() {
    /*commands.put("students",
        new StudentListCommand(new StudentService()));
    commands.put("add-student" , new AddStudent());
    commands.put("teacher-login",
        new LoginTeacherCommand(new TeacherService()));
    commands.put("exception" , new ExceptionCommand());*/
    commands.put("login", new GoToLoginPageCommand());
    commands.put("registration",new GoToRegistrationPageCommand());
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
