package service;

import dao.CustomerDao;
import dao.DaoFactory;
import dao.UserDao;
import entity.Customer;
import entity.Role;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class SignUpService {

  private DaoFactory factory = DaoFactory.getInstance();
  private AtomicReference<UserDao> userDao = new AtomicReference<>(factory.createUserDao());
  private AtomicReference<CustomerDao> customerDao = new AtomicReference<>(factory.createCustomerDao());


  public  boolean isExist(String enterLogin,String email) {
    return userDao.get().userIsExistByLoginEmail(enterLogin, email);
  }

  public void registerUser(String enterLogin, String enterPass, String email,
      String fullName) {
    Customer customer = new Customer();
    User user = new User(enterLogin,enterPass,email, Role.CUSTOMER);
    customer.setFIO(fullName);
    customer.setVisits(new ArrayList<>());
    customer.setUser(user);
    try {
      customerDao.get().create(customer);
      customerDao.get().close();
      userDao.get().close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
