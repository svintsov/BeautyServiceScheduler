package dao.mapper;

import entity.Role;
import entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 * User mapper implementation
 */
public class UserMapper implements ObjectMapper<User> {

  String ID;
  String LOGIN;
  String PASSWORD;
  String EMAIL;
  String FULL_NAME;
  String TYPE;

  public UserMapper(){
    this.ID="idusers";
    this.LOGIN="login";
    this.PASSWORD="password";
    this.FULL_NAME="full_name";
    this.EMAIL="email";
    this.TYPE="type";
  }

  /**
   * Extracts from result set and creates new User
   * @param rs
   * @return
   * @throws SQLException
   */
  @Override
  public User extractFromResultSet(final ResultSet rs) throws SQLException {
    User result = new User();
    result.setId(rs.getInt(ID));
    result.setLogin(rs.getString(LOGIN));
    result.setPassword(rs.getString(PASSWORD));
    result.setEmail(rs.getString(EMAIL));
    result.setFIO(rs.getString(FULL_NAME));
    result.setRole(Role.valueOf(rs.getString(TYPE)));
    result.setVisits(new ArrayList<>());
    return result;

  }

  /**
   * Checks for unique user
   * @param cache
   * @param user
   * @return
   */
  @Override
  public User makeUnique(Map<Integer, User> cache, User user) {

    cache.putIfAbsent(user.getId(), user);
    return cache.get(user.getId());
  }
}
