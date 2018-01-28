package dao.mapper;

import entity.BeautyServiceType;
import entity.State;
import entity.Visit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Map;

/**
 * Visit mapper implementation
 */
public class VisitMapper implements ObjectMapper<Visit> {

  private final String ID="idvisits";
  private final String DAY="day";
  private final String START="start_time";
  private final String STATE="state";
  private final String SERVICE_TYPE = "service";
  private final String REVIEW="review";

  /**
   * Retrieves from result set Visit entity
   * @param rs
   * @return
   * @throws SQLException
   */
  @Override
  public Visit extractFromResultSet(final ResultSet rs) throws SQLException {
    Visit result = new Visit();
    result.setId(rs.getInt(ID));
    result.setDay(rs.getDate(DAY).toLocalDate());
    result.setStart(LocalTime.parse(rs.getString(START)));
    result.setState(State.valueOf(rs.getString(STATE)));
    result.setBeautyServiceType(BeautyServiceType.valueOf(rs.getString(SERVICE_TYPE)));
    result.setReview(rs.getString(REVIEW));
    return result;
  }

  /**
   * Check Visit for unique
   * @param cache
   * @param visit
   * @return
   */
  @Override
  public Visit makeUnique(final Map<Integer, Visit> cache, final Visit visit) {
    cache.putIfAbsent(visit.getId(),visit);
    return cache.get(visit.getId());
  }
}
