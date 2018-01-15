package dao.mapper;

import entity.Role;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {

  @Override
  public User extractFromResultSet(ResultSet rs) throws SQLException {
    User result = new User();
    result.setId(rs.getInt("idcontacts"));
    result.setLogin(rs.getString("login"));
    result.setPassword(rs.getString("password"));
    result.setEmail(rs.getString("email"));
    result.setRole(Role.valueOf(rs.getString("role")));
    return result;

  }

  @Override
  public User makeUnique(Map<Integer, User> cache, User object) {
    return null;
  }
}
