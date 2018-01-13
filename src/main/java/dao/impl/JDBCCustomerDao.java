package dao.impl;

import dao.CustomerDao;
import entity.Customer;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCCustomerDao implements CustomerDao {

  private final Connection connection;

  public JDBCCustomerDao(Connection connection) {
    this.connection=connection;
  }

  @Override
  public void create(Customer model) throws SQLException {

  }

  @Override
  public Customer read(Integer integer) throws SQLException {
    return null;
  }

  @Override
  public void update(Customer model) throws SQLException {

  }

  @Override
  public void delete(Customer model) throws SQLException {

  }

  @Override
  public void close() {
    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
