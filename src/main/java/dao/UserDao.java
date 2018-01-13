package dao;

import entity.User;

public interface UserDao extends Dao<User,Integer> {
  User read(String login, String password);
  boolean userIsExist(String login, String password);
}
