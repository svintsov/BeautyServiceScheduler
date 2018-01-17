package dao.impl;

import bundle.SQLQueryManager;
import dao.VisitDao;
import dao.mapper.CustomerMapper;
import dao.mapper.MasterMapper;
import dao.mapper.UserMapper;
import dao.mapper.VisitMapper;
import entity.State;
import entity.User;
import entity.Visit;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
      st.executeUpdate(SQLVisit.SAFE_OFF.QUERY);
      st.executeUpdate(SQLVisit.DELETE_ALL.QUERY);
      st.executeUpdate(SQLVisit.SAFE_ON.QUERY);
    }
  }

  @Override
  public void update(Visit visit, State state) throws SQLException {
    try(PreparedStatement st = connection.prepareStatement(SQLVisit.UPDATE_STATE.QUERY)){
      st.setString(1,state.toString());
      st.setInt(2,visit.getId());
      st.executeUpdate();
    }
  }

  @Override
  public void update(Visit visit, User customer) throws SQLException {
    try(PreparedStatement st = connection.prepareStatement(SQLVisit.UPDATE_STATE.QUERY)){
      st.setInt(1,customer.getId());
      st.setInt(2,visit.getId());
      st.executeUpdate();
    }
  }

  @Override
  public void create(Visit model) throws SQLException {

  }

  @Override
  public Visit read(Integer id) throws SQLException {
    Visit result = new Visit();
    result.setId(-1);

    try(PreparedStatement st = connection.prepareStatement(SQLQueryManager.getProperty(SQLVisit.READ.QUERY))){
      st.setInt(1,id);
      final ResultSet rs = st.executeQuery();

      if(rs.next()){
        result = visitMapper.extractFromResultSet(rs);
      }
    }
    return result;
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

  @Override
  public Connection getConnection() {
    return this.connection;
  }

  enum SQLVisit {
    READ("visit.read.id"),
    READ_ALL("visit.read.all"),
    INSERT(""),
    DELETE(""),
    DELETE_ALL("visit.delete.all"),
    UPDATE_STATE("visit.update.state"),
    UPDATE_CUSTOMER("visit.update.customer"),
    SAFE_ON("sql.safe_mode.on"),
    SAFE_OFF("sql.safe_mode.off");

    String QUERY;

    SQLVisit(String QUERY) {
      this.QUERY = QUERY;
    }
  }
}
