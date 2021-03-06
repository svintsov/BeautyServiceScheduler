package dao.impl;

import dao.DaoFactory;
import dao.UserDao;
import dao.VisitDao;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 * Implementation of Factory pattern for Dao
 */
public class JDBCDaoFactory extends DaoFactory {

  private DataSource dataSource = ConnectionPoolHolder.getDataSource();

  @Override
  public UserDao createUserDao() {
    return new JDBCUserDao(getConnection());
  }

  @Override
  public VisitDao createVisitDao(){
    return new JDBCVisitDao(getConnection());
  }

  /**
   * Gets connection from connection pool holder
   * @return
   */
  public Connection getConnection(){
    try {
      return dataSource.getConnection();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}