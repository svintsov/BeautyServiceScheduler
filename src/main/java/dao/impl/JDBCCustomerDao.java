package dao.impl;

import dao.CustomerDao;
import dao.DaoFactory;
import dao.UserDao;
import entity.Customer;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCCustomerDao implements CustomerDao {

  private final Connection connection;

  public JDBCCustomerDao(Connection connection) {
    this.connection=connection;
  }

  private int createUserForClient(User user) throws  SQLException{
    UserDao dao = DaoFactory.getInstance().createUserDao();
    dao.create(user);
    return dao.readByLoginPassword(user.getLogin(),user.getPassword()).getId();

  }

  @Override
  public boolean isExist(int id) throws SQLException {
    try (PreparedStatement statement = connection.prepareStatement(SQLCustomer.GET_BY_ID.QUERY)) {
      statement.setInt(1, id);
      final ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        return true;
      }
    }
    return false;
  }


  @Override
  public void create(Customer model) throws SQLException {
    //TODO add exeption or message why we can't create a new user
    connection.setAutoCommit(false);
    if (!isExist(model.getId())) {
      try (PreparedStatement statement = connection.prepareStatement(SQLCustomer.INSERT.QUERY)) {
        statement.setString(1, model.getFIO());
        statement.setInt(2,createUserForClient(model.getUser()));
        statement.executeUpdate();
        connection.commit();
      } catch (SQLException e){
        connection.rollback();
      }
    }
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

  enum SQLCustomer {
    GET_BY_ID("SELECT * FROM clients WHERE idclients = (?)"),
    INSERT("INSERT INTO clients(full_name,idcontacts) VALUES((?),(?))"),
    DELETE(""),
    UPDATE("");

    String QUERY;

    SQLCustomer(String QUERY) {
      this.QUERY = QUERY;
    }
  }
}
