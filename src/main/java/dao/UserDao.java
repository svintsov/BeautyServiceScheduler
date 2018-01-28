package dao;

import entity.Role;
import entity.User;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface for User entity in Dao
 */
public interface UserDao extends Dao<User,Integer> {
  User read(String login) throws SQLException;
  List<User> findAll(Role role) throws SQLException;
}
