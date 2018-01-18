package dao;

import entity.State;
import entity.User;
import entity.Visit;
import java.sql.SQLException;
import java.util.List;

public interface VisitDao extends Dao<Visit,Integer> {
  List<Visit> findAll() throws SQLException;
  void deleteAll() throws SQLException;
  void delete(int id) throws SQLException;
  void update(Visit visit, State state) throws SQLException;
  void update(Visit visit, User customer) throws SQLException;
}
