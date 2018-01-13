package dao.impl;

import dao.CustomerDao;
import dao.DaoFactory;
import dao.MasterDao;
import dao.UserDao;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

public class JDBCDaoFactory extends DaoFactory {

  private DataSource dataSource = ConnectionPoolHolder.getDataSource();

  @Override
  public UserDao createUserDao() {
    return new JDBCUserDao(getConnection());
  }

  @Override
  public CustomerDao createCustomerDao() {
    return new JDBCCustomerDao(getConnection());
  }

  @Override
  public MasterDao createMasterDao(){
    return new JDBCMasterDao(getConnection());
  }

  private Connection getConnection(){
    try {
      return dataSource.getConnection();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}