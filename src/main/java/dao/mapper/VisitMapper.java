package dao.mapper;

import entity.BeautyServiceType;
import entity.State;
import entity.Visit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Map;

public class VisitMapper implements ObjectMapper<Visit> {

  private final String ID="idvisits";
  private final String DAY="day";
  private final String START="start_time";
  private final String STATE="state";
  private final String SERVICE_TYPE = "service";
  private final String REVIEW="review";

  @Override
  public Visit extractFromResultSet(ResultSet rs) throws SQLException {
    Visit result = new Visit();
    result.setId(rs.getInt(ID));
    result.setDay(rs.getDate(DAY).toLocalDate());
    result.setStart(LocalTime.parse(rs.getString(START)));
    result.setState(State.valueOf(rs.getString(STATE)));
    result.setBeautyServiceType(BeautyServiceType.valueOf(rs.getString(SERVICE_TYPE)));
    result.setReview(rs.getString(REVIEW));
    return result;
  }

  @Override
  public Visit makeUnique(Map<Integer, Visit> cache, Visit object) {
    return null;
  }
}
