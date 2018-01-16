package dao.mapper;

import entity.Role;
import entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {

  String ID="idusers";
  String LOGIN="login";
  String PASSWORD="password";
  String EMAIL="email";
  String FULL_NAME="full_name";
  String TYPE="type";

  @Override
  public User extractFromResultSet(ResultSet rs) throws SQLException {
    User result = new User();
    result.setId(rs.getInt(ID));
    result.setLogin(rs.getString(LOGIN));
    result.setPassword(rs.getString(PASSWORD));
    result.setEmail(rs.getString(EMAIL));
    result.setFIO(rs.getString(FULL_NAME));
    result.setRole(Role.valueOf(rs.getString(TYPE)));
    return result;

  }

  @Override
  public User makeUnique(Map<Integer, User> cache, User object) {
    return null;
  }
}
