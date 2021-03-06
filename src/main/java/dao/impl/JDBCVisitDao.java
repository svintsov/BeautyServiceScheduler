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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of Visit Dao
 */
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

  /**
   * Reads all visits from db
   * @return
   * @throws SQLException
   */
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

  /**
   * Reads all visits where state is FREE and Customer is undefined
   * @return
   * @throws SQLException
   */
  @Override
  public List<Visit> findAllWithoutCustomer() throws SQLException {
    Map<Integer, Visit> visits = new HashMap<>();
    Map<Integer, User> users = new HashMap<>();

    try (Statement st = connection.createStatement()) {
      ResultSet rs = st.executeQuery(SQLQueryManager.getProperty(SQLVisit.READ_ALL_WITHOUT_CUSTOMER.QUERY));

      while (rs.next()) {
        Visit visit = visitMapper
            .extractFromResultSet(rs);
        User master = masterMapper
            .extractFromResultSet(rs);
        visit = visitMapper
            .makeUnique(visits, visit);
        master = masterMapper
            .makeUnique(users, master);
        master.getVisits().add(visit);
        visit.setMaster(master);
      }
      return new ArrayList<>(visits.values());
    }
  }


  /**
   * Deletes all visits
   * @throws SQLException
   */
  @Override
  public void deleteAll() throws SQLException {
    try(Statement st = connection.createStatement()){
      st.executeUpdate(SQLQueryManager.getProperty(SQLVisit.SAFE_OFF.QUERY));
      st.executeUpdate(SQLQueryManager.getProperty(SQLVisit.DELETE_ALL.QUERY));
      st.executeUpdate(SQLQueryManager.getProperty(SQLVisit.SAFE_ON.QUERY));
    }
  }

  /**
   * Delete visit with specific id
   * @param id
   * @throws SQLException
   */
  @Override
  public void delete(final int id) throws SQLException {
    try(PreparedStatement st = connection.prepareStatement(SQLQueryManager.getProperty(SQLVisit.DELETE.QUERY))){
      st.setInt(1,id);
      st.executeUpdate();
    }
  }

  /**
   * Updates visit's state
   * @param id
   * @param state
   * @throws SQLException
   */
  @Override
  public void update(final int id, final State state) throws SQLException {
    try(PreparedStatement st = connection.prepareStatement(SQLQueryManager.getProperty(SQLVisit.UPDATE_STATE.QUERY))){
      st.setString(1,state.toString());
      st.setInt(2,id);
      st.executeUpdate();
    }
  }

  /**
   * Updates visit's customer
   * @param id
   * @param idCustomer
   * @throws SQLException
   */
  @Override
  public void update(final int id, final int idCustomer) throws SQLException {
    try(PreparedStatement st = connection.prepareStatement(SQLQueryManager.getProperty(SQLVisit.UPDATE_CUSTOMER.QUERY))){
      st.setInt(1,idCustomer);
      st.setInt(2,id);
      st.executeUpdate();
    }
  }

  /**
   * Updates visit's review
   * @param id
   * @param message
   * @throws SQLException
   */
  @Override
  public void update(final int id, final String message) throws SQLException {
    try(PreparedStatement st = connection.prepareStatement(SQLQueryManager.getProperty(SQLVisit.UPDATE_REVIEW.QUERY))){
      st.setString(1,message);
      st.setInt(2,id);
      st.executeUpdate();
    }
  }

  /**
   * Creates visit from inputted bundle
   * @param bundle
   * @throws SQLException
   */
  @Override
  public void create(final Map<String, String> bundle) throws SQLException {
    try(PreparedStatement st = connection.prepareStatement(SQLQueryManager.getProperty(SQLVisit.INSERT.QUERY))){
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
      Date date = Date.valueOf(bundle.get("day"));
      formatter.format(date);
      st.setDate(1,new java.sql.Date(date.getTime()));
      st.setString(2,bundle.get("hour"));
      st.setString(3,bundle.get("states_select"));
      st.setInt(4,Integer.valueOf(bundle.get("services_select")));
      st.setInt(5,Integer.valueOf(bundle.get("master")));
      if (bundle.get("customer").equals("-1")){
        st.setNull(6, Types.INTEGER);
      } else {
        st.setInt(6,Integer.valueOf(bundle.get("customer")));
      }
      st.executeUpdate();
    }
  }

  /**
   * Creates visit with Visit entity
   * @param model
   * @throws SQLException
   */
  @Override
  public void create(final Visit model) throws SQLException {

  }

  /**
   * Read visit with specific id
   * @param id
   * @return
   * @throws SQLException
   */
  @Override
  public Visit read(final Integer id) throws SQLException {
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

  /**
   * Updates visit with Visit entity
   * @param model
   * @throws SQLException
   */
  @Override
  public void update(final Visit model) throws SQLException {

  }

  /**
   * Deletes visit with ready model
   * @param model
   * @throws SQLException
   */
  @Override
  public void delete(final Visit model) throws SQLException {

  }

  /**
   * Closes db connection
   */
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

  /**
   * SQL scripts
   */
  enum SQLVisit {
    READ("visit.read.id"),
    READ_ALL("visit.read.all"),
    READ_ALL_WITHOUT_CUSTOMER("visit.read.all.without.customer"),
    INSERT("visit.create"),
    DELETE("visit.delete.id"),
    DELETE_ALL("visit.delete.all"),
    UPDATE_STATE("visit.update.state"),
    UPDATE_CUSTOMER("visit.update.customer"),
    UPDATE_REVIEW("visit.update.review"),
    SAFE_ON("sql.safe_mode.on"),
    SAFE_OFF("sql.safe_mode.off");

    String QUERY;

    SQLVisit(String QUERY) {
      this.QUERY = QUERY;
    }
  }
}
