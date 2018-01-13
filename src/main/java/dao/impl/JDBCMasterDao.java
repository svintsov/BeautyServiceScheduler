package dao.impl;

import dao.MasterDao;
import entity.Master;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCMasterDao implements MasterDao {
  private final Connection connection;

  public JDBCMasterDao(Connection connection) {
    this.connection=connection;
  }

  @Override
  public void create(Master model) {

  }

  @Override
  public Master read(Integer integer) {
    return null;
  }

  @Override
  public void update(Master model) {

  }

  @Override
  public void delete(Master model) {

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
