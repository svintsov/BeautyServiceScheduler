package service;

import dao.DaoFactory;
import dao.UserDao;
import entity.Role;
import entity.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;

public class LoginService {

  private DaoFactory factory = DaoFactory.getInstance();
  AtomicReference<UserDao> dao = new AtomicReference<>(factory.createUserDao());

  public Role getRole(String enterLogin, String enterPass) throws SQLException {
    Connection connection = dao.get().getConnection();
    connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
    User user = dao.get().read(enterLogin);
    if (isExist(user) && isPasswordCorrect(user,enterPass)){
      return user.getRole();
    } else {
      connection.close();
      throw new SQLException("Incorrect password or user is not exist!");
    }
  }

  private boolean isExist(User user){
    return user.getId()!=-1;
  }

  private boolean isPasswordCorrect(User user, String password){
    return user.getPassword().equals(password);
  }

}