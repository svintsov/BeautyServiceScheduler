package dao;

import entity.Customer;
import java.sql.SQLException;

public interface CustomerDao extends Dao<Customer,Integer> {
  boolean isExist(int id) throws SQLException;
}
