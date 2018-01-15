package entity;

import java.util.List;

public class User {

  private int id;
  private String FIO;
  private List<Visit> visits;
  private String login;
  private String password;
  private String email;
  private Role role;


  public User(String login, String password, String email, String FIO, Role role) {
    this.FIO = FIO;
    this.login = login;
    this.password = password;
    this.email = email;
    this.role = role;
  }

  public User(){ }

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

    User user = (User) o;

    if (id != user.id) {
      return false;
    }
    if (!FIO.equals(user.FIO)) {
      return false;
    }
    if (!visits.equals(user.visits)) {
      return false;
    }
    if (!login.equals(user.login)) {
      return false;
    }
    if (!password.equals(user.password)) {
      return false;
    }
    if (!email.equals(user.email)) {
      return false;
    }
    return role == user.role;
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