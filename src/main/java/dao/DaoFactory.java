package dao;

import dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {
  private static DaoFactory daoFactory;

  public abstract UserDao createUserDao();
  public abstract CustomerDao createCustomerDao();
  public abstract MasterDao createMasterDao();

  public static DaoFactory getInstance(){
    if( daoFactory == null ){
      synchronized (DaoFactory.class){
        if(daoFactory==null){
          daoFactory = new JDBCDaoFactory();
        }
      }
    }
    return daoFactory;
  }
}
