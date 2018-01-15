package dao.impl;

import dao.UserDao;
import dao.mapper.UserMapper;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUserDao implements UserDao {

  private Connection connection;
  private UserMapper mapper;

  public JDBCUserDao(Connection connection) {
    this.connection=connection;
    mapper = new UserMapper();
  }

  public Connection getConnection() {
    return connection;
  }

  @Override
  public void create(User model) throws SQLException {
    try (PreparedStatement statement = connection.prepareStatement(SQLUser.INSERT.QUERY)) {
      statement.setString(1, model.getLogin());
      statement.setString(2, model.getPassword());
      statement.setString(3, model.getEmail());
      statement.setString(4, model.getFIO());
      statement.setInt(5, model.getRole().getId());
      statement.executeUpdate();
    }

  }

  @Override
  public User read(Integer id) throws SQLException {
    User result = new User();
    result.setId(-1);

    try (PreparedStatement statement = connection.prepareStatement(SQLUser.GET_BY_ID.QUERY)) {
      statement.setInt(1, id);
      final ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        result = mapper.extractFromResultSet(rs);
      }
    }
    return result;
  }

  @Override
  public User read(String login) throws SQLException {
    User result = new User();
    result.setId(-1);

    try (PreparedStatement statement = connection.prepareStatement(SQLUser.GET_BY_LOGIN.QUERY)) {
      statement.setString(1, login);
      final ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        result = mapper.extractFromResultSet(rs);
      }
    }
    return result;
  }

  @Override
  public void update(User model) throws SQLException {

  }

  @Override
  public void delete(User model) throws SQLException {

  }

  @Override
  public void close() {
    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }



  enum SQLUser {
    GET_BY_ID("SELECT * FROM users JOIN roles USING(idroles) WHERE idusers = (?)"),
    GET_BY_LOGIN("SELECT * FROM users JOIN roles USING(idroles) WHERE login = (?)"),
    INSERT("INSERT INTO users(login,password,email,full_name,idroles) VALUES((?),(?),(?),(?),(?))"),
    DELETE(""),
    UPDATE("");

    String QUERY;

    SQLUser(String QUERY) {
      this.QUERY = QUERY;
    }
  }
}
