package service;

import dao.DaoFactory;
import dao.UserDao;
import entity.Role;
import entity.User;
import java.sql.Connection;
import java.sql.SQLException;

public class SignUpService {

  public void register(String login, String password, String email, String fullName) throws  SQLException{

    final UserDao dao = DaoFactory.getInstance().createUserDao();
    final Connection connection = dao.getConnection();
    connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
    connection.setAutoCommit(false);
    User user = dao.read(login);
    if (!isExist(user)){
      dao.create(new User(login,password,email,fullName, Role.CUSTOMER));
      connection.commit();
      dao.close();
    } else {
      connection.rollback();
      dao.close();
      throw new SQLException();
    }

  }

  private boolean isExist(User user){
    return user.getId()!=-1;
  }
}
