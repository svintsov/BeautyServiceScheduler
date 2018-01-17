package dao.impl;

import bundle.SQLQueryManager;
import dao.VisitDao;
import dao.mapper.CustomerMapper;
import dao.mapper.MasterMapper;
import dao.mapper.UserMapper;
import dao.mapper.VisitMapper;
import entity.User;
import entity.Visit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCVisitDao implements VisitDao {

  private UserMapper customerMapper;
  private UserMapper masterMapper;
  private VisitMapper visitMapper;
  private Connection connection;

  public JDBCVisitDao(Connection connection) {
    this.connection = connection;
    customerMapper = new CustomerMapper();
    masterMapper = new MasterMapper();
    visitMapper = new VisitMapper();
  }

  @Override
  public List<Visit> findAll() throws SQLException {
    Map<Integer, Visit> visits = new HashMap<>();
    Map<Integer, User> users = new HashMap<>();

    try (Statement st = connection.createStatement()) {
      ResultSet rs = st.executeQuery(SQLQueryManager.getProperty(SQLVisit.READ_ALL.QUERY));

      while (rs.next()) {
        Visit visit = visitMapper
            .extractFromResultSet(rs);
        User master = masterMapper
            .extractFromResultSet(rs);
        User customer = customerMapper
            .extractFromResultSet(rs);
        visit = visitMapper
            .makeUnique(visits, visit);
        master = masterMapper
            .makeUnique(users, master);
        customer = customerMapper
            .makeUnique(users, customer);
        master.getVisits().add(visit);
        customer.getVisits().add(visit);
        visit.setCustomer(customer);
        visit.setMaster(master);
      }
      return new ArrayList<>(visits.values());
    }
  }

  @Override
  public void deleteAll() throws SQLException {
    try(Statement st = connection.createStatement()){
      st.executeUpdate(SQLVisit.DELETE_ALL.QUERY);
    }
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
  public void close(){
    try {
      this.connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  enum SQLVisit {
    READ(""),
    READ_ALL("visit.read.all"),
    INSERT(""),
    DELETE(""),
    DELETE_ALL("visit.delete.all"),
    UPDATE("");

    String QUERY;

    SQLVisit(String QUERY) {
      this.QUERY = QUERY;
    }
  }
}
