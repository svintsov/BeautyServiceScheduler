package entity;

import java.util.List;

public class Customer {

  private int id;
  private String FIO;
  private List<Visit> visits;
  private String login;
  private String password;
  private String email;
  private Role role;

  public Customer(){ }

  public Customer(int id, String FIO, List<Visit> visits, String login, String password,
      String email) {
    this.id = id;
    this.FIO = FIO;
    this.visits = visits;
    this.login = login;
    this.password = password;
    this.email = email;
    this.role=Role.CUSTOMER;
  }

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


}
