package dao;

import dao.impl.JDBCDaoFactory;

/**
 * Factory to create dao implementation
 * Singleton pattern
 */
public abstract class DaoFactory {
  private static DaoFactory daoFactory;

  public abstract UserDao createUserDao();
  public abstract VisitDao createVisitDao();

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
