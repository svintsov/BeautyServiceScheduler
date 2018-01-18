package service;

import dao.DaoFactory;
import dao.VisitDao;
import entity.Visit;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class VisitService {
  private final AtomicReference<VisitDao> dao = new AtomicReference<>(DaoFactory.getInstance().createVisitDao());
  private final Connection connection = dao.get().getConnection();

  public List<Visit> getAllVisits() throws SQLException{
    connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
    List<Visit> result = dao.get().findAll();
    connection.close();
    return result;
  }

  public void deleteByID(int id) throws SQLException{

  }

}
