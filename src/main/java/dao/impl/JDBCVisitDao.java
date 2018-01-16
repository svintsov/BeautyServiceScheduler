package dao.impl;

import dao.VisitDao;
import entity.Visit;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JDBCVisitDao implements VisitDao {

  public JDBCVisitDao(Connection connection) {
  }

  @Override
  public List<Visit> findAll() {
    return null;
  }

  @Override
  public void create(Visit model) throws SQLException {

  }

  @Override
  public Visit read(Integer integer) throws SQLException {
    return null;
  }

  @Override
  public void update(Visit model) throws SQLException {

  }

  @Override
  public void delete(Visit model) throws SQLException {

  }

  @Override
  public void close() {

  }
}
