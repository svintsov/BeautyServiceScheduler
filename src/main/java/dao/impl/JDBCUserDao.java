package dao.impl;

import bundle.SQLQueryManager;
import dao.UserDao;
import dao.mapper.UserMapper;
import entity.Role;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of User Dao
 */
public class JDBCUserDao implements UserDao {

  private Connection connection;
  private UserMapper mapper;

  JDBCUserDao(Connection connection) {
    this.connection=connection;
    mapper = new UserMapper();
  }

  public Connection getConnection() {
    return connection;
  }

  /**
   * Creates user in database
   * @param model
   * @throws SQLException
   */
  @Override
  public void create(User model) throws SQLException {
    try (PreparedStatement statement = connection.prepareStatement(SQLQueryManager.getProperty(SQLUser.INSERT.QUERY))) {
      statement.setString(1, model.getLogin());
      statement.setString(2, model.getPassword());
      statement.setString(3, model.getEmail());
      statement.setString(4, model.getFIO());
      statement.setInt(5, model.getRole().getId());
      statement.executeUpdate();
    }

  }

  /**
   * Reads user from db by id
   * @param id
   * @return
   * @throws SQLException
   */
  @Override
  public User read(Integer id) throws SQLException {
    User result = new User();
    result.setId(-1);

    try (PreparedStatement statement = connection.prepareStatement(SQLQueryManager.getProperty(SQLUser.GET_BY_ID.QUERY))) {
      statement.setInt(1, id);
      final ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        result = mapper.extractFromResultSet(rs);
      }
    }
    return result;
  }

  /**
   * Reads user from db by login
   * @param login
   * @return
   * @throws SQLException
   */
  @Override
  public User read(String login) throws SQLException {
    User result = new User();
    result.setId(-1);

    try (PreparedStatement statement = connection.prepareStatement(SQLQueryManager.getProperty(SQLUser.GET_BY_LOGIN.QUERY))) {
      statement.setString(1, login);
      final ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        result = mapper.extractFromResultSet(rs);
      }
    }
    return result;
  }

  /**
   * Reads all users with specific role
   * @param role
   * @return
   * @throws SQLException
   */
  @Override
  public List<User> findAll(Role role) throws SQLException {
    try(PreparedStatement statement = connection.prepareStatement(SQLQueryManager.getProperty(SQLUser.GET_ALL_BY_ROLE.QUERY))){
      statement.setString(1,role.toString());
      final ResultSet rs = statement.executeQuery();
      List<User> result = new ArrayList<>();
      while(rs.next()){
        result.add(mapper.extractFromResultSet(rs));
      }
      return result;
    }

  }

  /**
   * Updates User with model
   * @param model
   * @throws SQLException
   */
  @Override
  public void update(User model) throws SQLException {

  }

  /**
   * Deletes User from db
   * @param model
   * @throws SQLException
   */
  @Override
  public void delete(User model) throws SQLException {

  }

  /**
   * Closes db connection
   */
  @Override
  public void close() {
    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


  /**
   * Holder of sql scripts
   */
  enum SQLUser {
    GET_BY_ID("user.read.id"),
    GET_BY_LOGIN("user.read.login"),
    GET_ALL_BY_ROLE("user.read.all.role"),
    INSERT("user.create"),
    DELETE(""),
    UPDATE("");

    String QUERY;

    SQLUser(String QUERY) {
      this.QUERY = QUERY;
    }
  }
}
