package entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Visit {

  private int id;
  private BeautyServiceType beautyServiceType;
  private User master;
  private LocalDate day;
  private LocalTime start;
  private User customer;
  private State state;
  private String review;

  public Visit() {
  }

  public Visit(int id, BeautyServiceType beautyServiceType, User master, LocalDate day,
      LocalTime start, User customer, State state, String review) {
    this.id = id;
    this.beautyServiceType = beautyServiceType;
    this.master = master;
    this.day = day;
    this.start = start;
    this.customer = customer;
    this.state = state;
    this.review = review;
  }

  public String getReview() {
    return review;
  }

  public void setReview(String review) {
    this.review = review;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public BeautyServiceType getBeautyServiceType() {
    return beautyServiceType;
  }

  public void setBeautyServiceType(BeautyServiceType beautyServiceType) {
    this.beautyServiceType = beautyServiceType;
  }

  public User getMaster() {
    return master;
  }

  public void setMaster(User master) {
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

  public User getCustomer() {
    return customer;
  }

  public void setCustomer(User customer) {
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

    return id == visit.id;
  }

  @Override
  public int hashCode() {
    return id;
  }

  @Override
  public String toString() {
    return "Visit{" +
        "id=" + id +
        ", beautyServiceType=" + beautyServiceType +
        ", master=" + master +
        ", day=" + day +
        ", start=" + start +
        ", customer=" + customer +
        ", state=" + state +
        ", review='" + review + '\'' +
        '}';
  }
}