package dao;

import entity.Visit;
import java.sql.SQLException;
import java.util.List;

public interface VisitDao extends Dao<Visit,Integer> {
  List<Visit> findAll() throws SQLException;
  void deleteAll() throws SQLException;
}
