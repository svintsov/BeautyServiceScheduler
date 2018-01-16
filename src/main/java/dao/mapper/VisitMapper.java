package dao.mapper;

import entity.Visit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class VisitMapper implements ObjectMapper<Visit> {

  @Override
  public Visit extractFromResultSet(ResultSet rs) throws SQLException {
    return null;
  }

  @Override
  public Visit makeUnique(Map<Integer, Visit> cache, Visit object) {
    return null;
  }
}
