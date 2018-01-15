package dao;

public interface UserDao extends Dao<User,Integer> {
  User readByLoginPassword(String login, String password);
  User readByLoginEmail(String login, String email);
  boolean userIsExistByLoginPass(final String name,final String password);
  boolean userIsExistByLoginEmail(final String name, final String email);

}
