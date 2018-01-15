package dao;

import entity.User;
import java.sql.Connection;
import java.sql.SQLException;

public interface UserDao extends Dao<User,Integer> {
  User read(String login) throws SQLException;
  Connection getConnection();
}
