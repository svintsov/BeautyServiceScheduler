import static org.hamcrest.CoreMatchers.is;

import dao.DaoFactory;
import dao.VisitDao;
import entity.Visit;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VisitDaoTest {

  VisitDao dao;
  Connection connection;

  @Before
  public void before(){
    this.dao= DaoFactory.getInstance().createVisitDao();
    this.connection =dao.getConnection();
  }

  @After()
  public void after(){
    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testReadVisitByID() throws SQLException{
    Visit expected = new Visit();
    expected.setId(1);
    Visit actual = dao.read(1);
    Assert.assertThat(actual,is(expected));
  }

  @Test
  public void testFindAllVisitsForAdmin() throws SQLException{
    connection.setAutoCommit(false);
    try {
      List<Visit> list = dao.findAll();
      printAllVisits(list);
    } finally {
      connection.rollback();
    }
  }

  private void printAllVisits(List<Visit> visits){
    for(Visit visit: visits){
      System.out.println(visit.toString());
    }
  }

}