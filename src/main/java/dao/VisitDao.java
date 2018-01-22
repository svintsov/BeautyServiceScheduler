package dao;

import entity.State;
import entity.Visit;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface VisitDao extends Dao<Visit,Integer> {
  List<Visit> findAll() throws SQLException;
  List<Visit> findAllWithoutCustomer() throws SQLException;
  void deleteAll() throws SQLException;
  void delete(int id) throws SQLException;
  void update(int id, State state) throws SQLException;
  void update(int id, int idCustomer) throws SQLException;
  void create(Map<String,String> bundle) throws SQLException;
}
