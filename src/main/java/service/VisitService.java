package service;

import dao.DaoFactory;
import dao.VisitDao;
import entity.Visit;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class VisitService {
  AtomicReference<VisitDao> dao = new AtomicReference<>(DaoFactory.getInstance().createVisitDao());

  public List<Visit> getAllVisits() throws SQLException{
    Connection connection = dao.get().getConnection();
    connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
    List<Visit> result = dao.get().findAll();
    connection.close();
    return result;
  }

}
