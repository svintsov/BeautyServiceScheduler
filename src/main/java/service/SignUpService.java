package service;

import dao.DaoFactory;
import dao.UserDao;
import entity.Role;
import entity.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;

public class SignUpService {
  private AtomicReference<UserDao> dao = new AtomicReference<>(DaoFactory.getInstance().createUserDao());

  public void register(String login, String password, String email, String fullName) throws  SQLException{

    Connection connection = dao.get().getConnection();
    connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
    connection.setAutoCommit(false);
    User user = dao.get().read(login);
    if (!isExist(user)){
      dao.get().create(new User(login,password,email,fullName, Role.CUSTOMER));
      connection.commit();
      connection.setAutoCommit(true);
      connection.close();
    } else {
      connection.rollback();
      connection.setAutoCommit(true);
      connection.close();
      throw new SQLException();
    }

  }

  private boolean isExist(User user){
    return user.getId()!=-1;
  }
}
