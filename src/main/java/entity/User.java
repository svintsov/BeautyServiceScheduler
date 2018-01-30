package entity;

import java.util.List;

/**
 * Entity for all users of service. Stores info about login,pass,email and name
 */
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

    return id == user.id;
  }

  @Override
  public int hashCode() {
    return id;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", FIO='" + FIO + '\'' +
        ", visits=" + visits +
        ", login='" + login + '\'' +
        ", password='" + password + '\'' +
        ", email='" + email + '\'' +
        ", role=" + role +
        '}';
  }
}