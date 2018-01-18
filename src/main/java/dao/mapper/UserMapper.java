package dao.mapper;

import entity.Role;
import entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {

  protected String ID;
  protected String LOGIN;
  protected String PASSWORD;
  protected String EMAIL;
  protected String FULL_NAME;
  protected String TYPE;

  public UserMapper(){
    this.ID="idusers";
    this.LOGIN="login";
    this.PASSWORD="password";
    this.FULL_NAME="full_name";
    this.EMAIL="email";
    this.TYPE="type";
  }

  @Override
  public User extractFromResultSet(ResultSet rs) throws SQLException {
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

  @Override
  public User makeUnique(Map<Integer, User> cache, User user) {

    cache.putIfAbsent(user.getId(), user);
    return cache.get(user.getId());
  }
}
