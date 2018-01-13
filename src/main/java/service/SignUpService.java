package service;

import dao.UserDaoMock;
import entity.Role;
import entity.User;
import java.util.concurrent.atomic.AtomicReference;

public class SignUpService {

  public static boolean isExist(String enterLogin, String enterPass,
      AtomicReference<UserDaoMock> dao) {
    return dao.get().userIsExist(enterLogin, enterPass);
  }

  public static void registerUser(String enterLogin, String enterPass, String email,
      String fullName, AtomicReference<UserDaoMock> dao) {
    dao.get().add(new User(3, enterLogin, enterPass, email, Role.CUSTOMER));
  }
}
