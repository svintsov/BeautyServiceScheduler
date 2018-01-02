package entity;

import java.util.List;

public class Customer {

  private int id;
  private String FIO;
  private List<Visit> visits;
  private User user;

  public Customer(){

  }

  public Customer(int id, String FIO, List<Visit> visits, User user) {
    this.id = id;
    this.FIO = FIO;
    this.visits = visits;
    this.user = user;
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

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Customer customer = (Customer) o;

    if (id != customer.id) {
      return false;
    }
    if (!FIO.equals(customer.FIO)) {
      return false;
    }
    if (visits != null ? !visits.equals(customer.visits) : customer.visits != null) {
      return false;
    }
    return user.equals(customer.user);
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + FIO.hashCode();
    result = 31 * result + (visits != null ? visits.hashCode() : 0);
    result = 31 * result + user.hashCode();
    return result;
  }
}
