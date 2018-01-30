package dao.impl;

import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;

/**
 * Connection pool storage and settings
 */
public class ConnectionPoolHolder {
  private static volatile DataSource dataSource;

  public static DataSource getDataSource(){

    if (dataSource == null){
      synchronized (ConnectionPoolHolder.class) {
        if (dataSource == null) {
          BasicDataSource ds = new BasicDataSource();
          ds.setUrl("jdbc:mysql://localhost:3306/mydb_test");
          ds.setUsername("root");
          ds.setPassword("qwaszx45");
          ds.setMinIdle(5);
          ds.setMaxIdle(10);
          ds.setMaxOpenPreparedStatements(100);
          ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
          ds.setConnectionProperties("useUnicode=yes;characterEncoding=utf8");
          dataSource = ds;
        }
      }
    }
    return dataSource;

  }


}
