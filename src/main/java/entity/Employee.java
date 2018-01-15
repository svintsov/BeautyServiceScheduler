package entity;

import java.util.List;

public class Employee {

  private int id;
  private String FIO;
  private List<Visit> visits;
  private String login;
  private String password;
  private String email;
  private Role role;


  public Employee(int id, String FIO, List<Visit> visits, String login, String password,
      String email, Role role) {
    this.id = id;
    this.FIO = FIO;
    this.visits = visits;
    this.login = login;
    this.password = password;
    this.email = email;
    this.role = role;
  }

  public Employee(){ }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFIO() {
    return FIO;
  }

  public void setFIO(String FIO) {
    this.FIO = FIO;
  }

  public List<Visit> getVisits() {
    return visits;
  }

  public void setVisits(List<Visit> visits) {
    this.visits = visits;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Employee employee = (Employee) o;

    if (id != employee.id) {
      return false;
    }
    if (!FIO.equals(employee.FIO)) {
      return false;
    }
    if (!visits.equals(employee.visits)) {
      return false;
    }
    if (!login.equals(employee.login)) {
      return false;
    }
    if (!password.equals(employee.password)) {
      return false;
    }
    if (!email.equals(employee.email)) {
      return false;
    }
    return role == employee.role;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + FIO.hashCode();
    result = 31 * result + visits.hashCode();
    result = 31 * result + login.hashCode();
    result = 31 * result + password.hashCode();
    result = 31 * result + email.hashCode();
    result = 31 * result + role.hashCode();
    return result;
  }
}