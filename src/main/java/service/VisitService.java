package service;

import dao.DaoFactory;
import dao.VisitDao;
import entity.BeautyServiceType;
import entity.Role;
import entity.State;
import entity.Visit;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Almost all function with Visit entity contains here
 */
public class VisitService {

  /**
   * Reads all visits from db
   * @return
   * @throws SQLException
   */
  public List<Visit> getAllVisits() throws SQLException{
    final VisitDao dao = DaoFactory.getInstance().createVisitDao();
    dao.getConnection().setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

    final List<Visit> result = dao.findAll();

    dao.close();
    return result;
  }

  /**
   * Reads all visits for specific user's role and id
   * @param idUser
   * @param role
   * @return
   * @throws SQLException
   */
  public List<Visit> getAllVisitsForUser(final int idUser, final Role role) throws SQLException{
    final VisitDao dao = DaoFactory.getInstance().createVisitDao();
    dao.getConnection().setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

    final List<Visit> result = dao.findAll();
    if (role.equals(Role.MASTER)){
      return result.stream().filter(v -> v.getMaster().getId()==idUser).collect(Collectors.toList());
    } else if (role.equals(Role.CUSTOMER)) {
      return result.stream().filter(v -> v.getCustomer().getId()==idUser).collect(Collectors.toList());
    } else {
      return result;
    }
  }

  /**
   * Reads all visit for specific date
   * @param type
   * @param date
   * @return
   * @throws IOException
   * @throws SQLException
   */
  public List<Visit> getAllVisitsForDate(final String type, final String date) throws IOException, SQLException{
    if (isDateInPast(date)) throw new IOException();

    final VisitDao dao = DaoFactory.getInstance().createVisitDao();
    dao.getConnection().setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
    final LocalDate localDate = LocalDate.parse(date);
    final BeautyServiceType beautyType = BeautyServiceType.valueOf(type);

    final List<Visit> result = dao.findAllWithoutCustomer();
    return result.stream()
        .filter(visit -> visit.getDay().equals(localDate))
        .filter(visit -> visit.getBeautyServiceType().equals(beautyType))
        .filter(visit -> visit.getState().equals(State.FREE))
        .collect(Collectors.toList());
  }

  /**
   * Deletes visit by id
   * @param id
   * @throws SQLException
   */
  public void deleteByID(final int id) throws SQLException{
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

  /**
   * Finishes visit by id
   * @param id
   * @throws SQLException
   */
  public void finishVisitByID(final int id) throws SQLException{
    final VisitDao dao = DaoFactory.getInstance().createVisitDao();
    final Connection connection = dao.getConnection();
    connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
    connection.setAutoCommit(false);

    final Visit visit = dao.read(id);
    if (visit.getState().equals(State.FREE) || visit.getState().equals(State.DONE)) throw new SQLException("message.visit.done_error");
    try {

      dao.update(id, State.DONE);
      connection.commit();
      dao.close();

    } catch (SQLException e){
      connection.rollback();
      dao.close();
      throw new SQLException("message.visit.update_state_error");
    }
  }

  /**
   * Write some message to visit review
   * @param id
   * @param message
   * @throws SQLException
   */
  public void writeReviewForVisit(final int id, final String message) throws SQLException{
    final VisitDao dao = DaoFactory.getInstance().createVisitDao();
    final Connection connection = dao.getConnection();
    connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
    connection.setAutoCommit(false);

    final Visit visit = dao.read(id);
    if (!visit.getState().equals(State.DONE)) throw new SQLException("message.visit.review_error");
    try {
      dao.update(id, message);
      connection.commit();
      dao.close();
    } catch (SQLException e){
      connection.rollback();
      dao.close();
      throw new SQLException("message.visit.review_error");
    }
  }

  /**
   * Reserves visit by some customer
   * @param idVisit
   * @param idUser
   * @throws SQLException
   */
  public void reserveVisitByID(final  int idVisit, final int idUser) throws SQLException{
    final VisitDao dao = DaoFactory.getInstance().createVisitDao();
    final Connection connection = dao.getConnection();
    connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
    connection.setAutoCommit(false);

    final Visit visit = dao.read(idVisit);

    if (visit.getState().equals(State.AGREED) || visit.getState().equals(State.DONE)) throw new SQLException("message.visit.done_error");
    try {

      dao.update(idVisit, State.AGREED);
      dao.update(idVisit,idUser);
      connection.commit();
      dao.close();

    } catch (SQLException e){
      connection.rollback();
      dao.close();
      throw new SQLException("message.visit.update_state_error");
    }
  }

  /**
   * Creates visit from map of data
   * @param visit
   * @throws SQLException
   * @throws IOException
   */
  public void createVisit(final Map<String,String> visit) throws SQLException,IOException{
    if (isDateInPast(visit.get("day"))) throw new IOException();
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

  /**
   * Checks if chosen date by user is in past
   * @param date
   * @return
   */
  private boolean isDateInPast(final String date){
    try {
      if (new SimpleDateFormat("yyyy-mm-dd").parse(date).before(new Date())) {
        return true;
      }
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return false;
  }


}
