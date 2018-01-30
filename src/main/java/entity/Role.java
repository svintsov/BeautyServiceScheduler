package entity;

/**
 * Roles of service
 */
public enum Role {
  ADMINISTRATOR(1),CUSTOMER(3),MASTER(2),UNKNOWN(0);

  private int id;

  Role(int id){
    this.id=id;
  }

  public int getId() {
    return id;
  }

}
