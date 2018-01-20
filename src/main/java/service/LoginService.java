package service;

import com.sun.istack.internal.NotNull;
import dao.DaoFactory;
import dao.UserDao;
import entity.User;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginService {

  public User getUserByLogin(final String enterLogin, final String enterPass) throws SQLException {
    final UserDao dao = DaoFactory.getInstance().createUserDao();
    final Connection connection = dao.getConnection();
    connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
    final User user = dao.read(enterLogin);
    dao.close();
    if (isExist(user) && isPasswordCorrect(user,enterPass)){
      return user;
    } else throw new SQLException();
  }

  private boolean isExist(@NotNull final User user){
    return user.getId()!=-1;
  }

  private boolean isPasswordCorrect(@NotNull final User user, final String password){
    return user.getPassword().equals(password);
  }

}