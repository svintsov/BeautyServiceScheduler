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

public class VisitService {

  public List<Visit> getAllVisits() throws SQLException{
    final VisitDao dao = DaoFactory.getInstance().createVisitDao();
    dao.getConnection().setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

    final List<Visit> result = dao.findAll();

    dao.close();
    return result;
  }

  public List<Visit> getAllVisitsForUser(final int id, final Role role) throws SQLException{
    final VisitDao dao = DaoFactory.getInstance().createVisitDao();
    dao.getConnection().setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

    final List<Visit> result = dao.findAll();
    if (role.equals(Role.MASTER)){
      return result.stream().filter(v -> v.getMaster().getId()==id).collect(Collectors.toList());
    } else if (role.equals(Role.CUSTOMER)) {
      return result.stream().filter(v -> v.getCustomer().getId()==id).collect(Collectors.toList());
    } else {
      return result;
    }
  }

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

  public void reserveVisitByID(final  int idvisit, final int iduser) throws SQLException{
    final VisitDao dao = DaoFactory.getInstance().createVisitDao();
    final Connection connection = dao.getConnection();
    connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
    connection.setAutoCommit(false);

    final Visit visit = dao.read(idvisit);

    if (visit.getState().equals(State.AGREED) || visit.getState().equals(State.DONE)) throw new SQLException("message.visit.done_error");
    try {

      dao.update(idvisit, State.AGREED);
      dao.update(idvisit,iduser);
      connection.commit();
      dao.close();

    } catch (SQLException e){
      connection.rollback();
      dao.close();
      throw new SQLException("message.visit.update_state_error");
    }
  }

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
