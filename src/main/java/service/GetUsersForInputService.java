package service;

import dao.DaoFactory;
import dao.UserDao;
import entity.Role;
import entity.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GetUsersForInputService {

  public List<User> getAllUsers(final Role role) throws SQLException{
    final UserDao dao = DaoFactory.getInstance().createUserDao();
    dao.getConnection().setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

    List<User> users = dao.findAll(role);

    dao.close();
    return users;
  }

}
