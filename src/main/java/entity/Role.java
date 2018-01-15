package entity;

import java.util.HashMap;
import java.util.Map;

public enum Role {
  ADMINISTRATOR(1),CUSTOMER(3),MASTER(2),UNKNOWN(0);

  private int id;

  Role(int id){
    this.id=id;
  }

  public int getId() {
    return id;
  }

  private static final Map<Integer,Role> map;
  static {
    map = new HashMap<Integer,Role>();
    for (Role v : Role.values()) {
      map.put(v.id, v);
    }
  }
  public static Role findByKey(int i) {
    return map.get(i);
  }
}
