package dao.mapper;

import entity.Role;
import entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {

  @Override
  public User extractFromResultSet(ResultSet rs) throws SQLException {
    User result = new User();
    result.setId(rs.getInt("idusers"));
    result.setLogin(rs.getString("login"));
    result.setLogin(rs.getString("password"));
    result.setLogin(rs.getString("email"));
    result.setLogin(rs.getString("full_name"));
    result.setRole((Role.findByKey(rs.getInt("type"))));
    return result;

  }

  @Override
  public User makeUnique(Map<Integer, User> cache, User object) {
    return null;
  }
}
