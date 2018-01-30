package dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Main interface for DAO patter
 * @param <Entity>
 * @param <Key>
 */
public interface Dao<Entity, Key> extends AutoCloseable{
  void create(Entity model) throws SQLException;
  Entity read(Key key) throws SQLException;
  void update(Entity model) throws SQLException;
  void delete(Entity model) throws SQLException;
  void close();
  Connection getConnection();
}