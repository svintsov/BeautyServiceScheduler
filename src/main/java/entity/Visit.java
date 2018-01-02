package entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Visit {

  private int id;
  private BeautyService beautyServiceType;
  private Master master;
  private LocalDate day;
  private LocalTime start;
  private Customer customer;
  private State state;

  public Visit(){

  }

  public Visit(int id, BeautyService beautyServiceType, Master master, LocalDate day,
      LocalTime start, Customer customer, State state) {
    this.id = id;
    this.beautyServiceType = beautyServiceType;
    this.master = master;
    this.day = day;
    this.start = start;
    this.customer = customer;
    this.state = state;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public BeautyService getBeautyServiceType() {
    return beautyServiceType;
  }

  public void setBeautyServiceType(BeautyService beautyServiceType) {
    this.beautyServiceType = beautyServiceType;
  }

  public Master getMaster() {
    return master;
  }

  public void setMaster(Master master) {
    this.master = master;
  }

  public LocalDate getDay() {
    return day;
  }

  public void setDay(LocalDate day) {
    this.day = day;
  }

  public LocalTime getStart() {
    return start;
  }

  public void setStart(LocalTime start) {
    this.start = start;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Visit visit = (Visit) o;

    if (id != visit.id) {
      return false;
    }
    if (beautyServiceType != visit.beautyServiceType) {
      return false;
    }
    if (!master.equals(visit.master)) {
      return false;
    }
    if (!day.equals(visit.day)) {
      return false;
    }
    if (!start.equals(visit.start)) {
      return false;
    }
    if (!customer.equals(visit.customer)) {
      return false;
    }
    return state == visit.state;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + beautyServiceType.hashCode();
    result = 31 * result + master.hashCode();
    result = 31 * result + day.hashCode();
    result = 31 * result + start.hashCode();
    result = 31 * result + customer.hashCode();
    result = 31 * result + state.hashCode();
    return result;
  }
}
