package service;

import dao.DaoFactory;
import dao.VisitDao;
import entity.State;
import entity.Visit;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class VisitService {

  public List<Visit> getAllVisits() throws SQLException{
    final AtomicReference<VisitDao> dao = new AtomicReference<>(DaoFactory.getInstance().createVisitDao());
    dao.get().getConnection().setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
    List<Visit> result = dao.get().findAll();
    return result;
  }

  public void deleteByID(int id) throws SQLException{
    final AtomicReference<VisitDao> dao = new AtomicReference<>(DaoFactory.getInstance().createVisitDao());
    final Connection connection = dao.get().getConnection();
    connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
    connection.setAutoCommit(false);
    try {
      dao.get().delete(id);
      connection.commit();
      connection.setAutoCommit(true);
    } catch (SQLException e){
      connection.rollback();
      throw new SQLException();
    } finally {
      connection.close();
    }
  }

  public void updateStateByID(int id) throws SQLException{
    final AtomicReference<VisitDao> dao = new AtomicReference<>(DaoFactory.getInstance().createVisitDao());
    final Connection connection = dao.get().getConnection();
    connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
    connection.setAutoCommit(false);
    try {
      dao.get().update(id, State.DONE);
      connection.commit();
      connection.setAutoCommit(true);
    } catch (SQLException e){
      connection.rollback();
      throw new SQLException();
    } finally {
      connection.close();
    }
  }

  public void createVisit(Visit visit) throws SQLException{

  }

}
