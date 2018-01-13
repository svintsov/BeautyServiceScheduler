package dao.impl;

import dao.UserDao;
import entity.Role;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUserDao implements UserDao {
  private final Connection connection;

  public JDBCUserDao(Connection connection) {
    this.connection = connection;
  }

  public boolean userIsExist(final String name,final String password) {
    return read(name,password).getId() != -1;
  }


  @Override
  public void create(User user) throws SQLException {
    //TODO add exeption or message why we can't create a new user
    if (!userIsExist(user.getLogin(),user.getPassword())) {
      try (PreparedStatement statement = connection.prepareStatement(SQLUser.INSERT.QUERY)) {
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getEmail());
        statement.setString(4, user.getRole().toString());
        statement.executeUpdate();
      }
    }
    else {
      throw new SQLException("Dublicated user");
    }

  }

  @Override
  public User read(Integer id) throws SQLException {
    final User result = new User();
    result.setId(-1);

    try (PreparedStatement statement = connection.prepareStatement(SQLUser.GET_BY_ID.QUERY)) {
      statement.setInt(1, id);
      final ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        result.setId(Integer.parseInt(rs.getString("idcontacts")));
        result.setLogin(rs.getString("login"));
        result.setPassword(rs.getString("password"));
        result.setEmail(rs.getString("email"));
        result.setRole(Role.valueOf(rs.getString("role")));
      }
    }
    return result;
  }

  public User read(String login,String password) {
    final User result = new User();
    result.setId(-1);

    try (PreparedStatement statement = connection.prepareStatement(SQLUser.GET_BY_LOGIN_PASSWORD.QUERY)) {
      statement.setString(1, login);
      statement.setString(2,password);
      final ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        result.setId(Integer.parseInt(rs.getString("idcontacts")));
        result.setLogin(login);
        result.setPassword(rs.getString("password"));
        result.setEmail(rs.getString("email"));
        result.setRole(Role.valueOf(rs.getString("role")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  @Override
  public void update(User model) {

  }

  @Override
  public void delete(User model) {

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
    GET_BY_ID("SELECT * FROM contacts WHERE idcontacts = (?)"),
    GET_BY_LOGIN_PASSWORD("SELECT * FROM contacts WHERE login = (?) AND password = (?)"),
    INSERT("INSERT INTO contacts(email,login,password,role) VALUES((?),(?),(?),(?))"),
    DELETE(""),
    UPDATE("");

    String QUERY;

    SQLUser(String QUERY) {
      this.QUERY = QUERY;
    }
  }
}