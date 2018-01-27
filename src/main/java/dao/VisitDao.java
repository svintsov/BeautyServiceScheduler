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
  void delete(final int id) throws SQLException;
  void update(final int id, final State state) throws SQLException;
  void update(final int id, final int idCustomer) throws SQLException;
  void update(final int id, final String message) throws SQLException;
  void create(final Map<String,String> bundle) throws SQLException;
}
