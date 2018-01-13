package service;

import dao.DaoFactory;
import dao.UserDao;
import entity.Role;
import java.util.concurrent.atomic.AtomicReference;

public class LoginService {

  private DaoFactory factory = DaoFactory.getInstance();
  private AtomicReference<UserDao> dao = new AtomicReference<>(factory.createUserDao());

  public Role getRole(String enterLogin, String enterPass) {
    return dao.get().read(enterLogin,enterPass).getRole();
    //return dao.get().getRoleByLoginPassword(enterLogin, enterPass);
  }

  public boolean checkLogin(String enterLogin, String enterPass){
    return dao.get().userIsExist(enterLogin,enterPass);
  }

}