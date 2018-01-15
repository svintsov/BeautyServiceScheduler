package dao.impl;

import dao.UserDao;
import dao.mapper.UserMapper;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUserDao implements UserDao {
  private final Connection connection;
  private final UserMapper userMapper;

  public JDBCUserDao(Connection connection) {
    this.connection = connection;
    userMapper = new UserMapper();
  }

  public boolean userIsExistByLoginPass(final String name,final String password) {
    return readByLoginPassword(name,password).getId() != -1;
  }

  public boolean userIsExistByLoginEmail(final String name, final String email){
    return readByLoginEmail(name,email).getId() != -1;
  }


  @Override
  public void create(User user) throws SQLException {
    //TODO add exeption or message why we can't create a new user
    if (!userIsExistByLoginPass(user.getLogin(),user.getPassword())) {
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
    User result = new User();
    result.setId(-1);

    try (PreparedStatement statement = connection.prepareStatement(SQLUser.GET_BY_ID.QUERY)) {
      statement.setInt(1, id);
      final ResultSet rs = statement.executeQuery();
      if (rs.next()) {

        result = userMapper.extractFromResultSet(rs);
      }
    }
    return result;
  }

  public User readByLoginPassword(String login,String password) {
    User result = new User();
    result.setId(-1);

    try (PreparedStatement statement = connection.prepareStatement(SQLUser.GET_BY_LOGIN_PASSWORD.QUERY)) {
      statement.setString(1, login);
      statement.setString(2,password);
      final ResultSet rs = statement.executeQuery();

      if (rs.next()) {

        result = userMapper.extractFromResultSet(rs);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  public User readByLoginEmail(String login,String email) {
    User result = new User();
    result.setId(-1);

    try (PreparedStatement statement = connection.prepareStatement(SQLUser.GET_BY_LOGIN_EMAIL.QUERY)) {
      statement.setString(1, login);
      statement.setString(2,email);
      final ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        result = userMapper.extractFromResultSet(rs);
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
    GET_BY_LOGIN_EMAIL("SELECT * FROM contacts WHERE login = (?) AND email = (?)"),
    INSERT("INSERT INTO contacts(login,password,email,role) VALUES((?),(?),(?),(?))"),
    DELETE(""),
    UPDATE("");

    String QUERY;

    SQLUser(String QUERY) {
      this.QUERY = QUERY;
    }
  }
}