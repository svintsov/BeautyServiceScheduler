package service;

import dao.UserDao;
import entity.Role;
import java.util.concurrent.atomic.AtomicReference;

public class LoginService {


  public static Role getRole(String enterLogin, String enterPass, AtomicReference<UserDao> dao) {
    return dao.get().getRoleByLoginPassword(enterLogin, enterPass);
  }

  public static boolean checkLogin(String enterLogin, String enterPass, AtomicReference<UserDao> dao){
    return dao.get().userIsExist(enterLogin,enterPass);
  }

}