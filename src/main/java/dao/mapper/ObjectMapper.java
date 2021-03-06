package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Main interface of Mapper pattern
 * @param <T>
 */
public interface ObjectMapper<T> {

  T extractFromResultSet(ResultSet rs) throws SQLException;

  T makeUnique(Map<Integer, T> cache,
      T object);
}