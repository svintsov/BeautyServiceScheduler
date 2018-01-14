package dao.mapper;

import entity.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class CustomerMapper implements ObjectMapper<Customer> {

  @Override
  public Customer extractFromResultSet(ResultSet rs) throws SQLException {
    return null;
  }

  @Override
  public Customer makeUnique(Map<Integer, Customer> cache, Customer object) {
    return null;
  }
}
