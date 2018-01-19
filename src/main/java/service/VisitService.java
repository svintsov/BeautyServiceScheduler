package service;

import dao.DaoFactory;
import dao.VisitDao;
import entity.State;
import entity.Visit;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class VisitService {

  public List<Visit> getAllVisits() throws SQLException{
    final VisitDao dao = DaoFactory.getInstance().createVisitDao();
    dao.getConnection().setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
    final List<Visit> result = dao.findAll();
    dao.close();
    return result;
  }

  public void deleteByID(int id) throws SQLException{
    final VisitDao dao = DaoFactory.getInstance().createVisitDao();
    final Connection connection = dao.getConnection();
    connection.setAutoCommit(false);
    try {
      dao.delete(id);
      connection.commit();
      dao.close();
    } catch (SQLException e){
      connection.rollback();
      dao.close();
      throw new SQLException();
    }
  }

  public void updateStateByID(int id) throws SQLException{
    final VisitDao dao = DaoFactory.getInstance().createVisitDao();
    final Connection connection = dao.getConnection();
    connection.setAutoCommit(false);
    try {
      dao.update(id, State.DONE);
      connection.commit();
      dao.close();
    } catch (SQLException e){
      connection.rollback();
      dao.close();
      throw new SQLException();
    }
  }

  public void createVisit(Map<String,String> visit) throws SQLException{
    final VisitDao dao = DaoFactory.getInstance().createVisitDao();
    final Connection connection = dao.getConnection();
    connection.setAutoCommit(false);
    try {
      dao.create(visit);
      connection.commit();
      dao.close();
    } catch (SQLException e){
      connection.rollback();
      dao.close();
      throw new SQLException();
    }

  }

}
