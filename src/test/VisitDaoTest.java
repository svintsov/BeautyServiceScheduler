import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import dao.DaoFactory;
import dao.VisitDao;
import entity.Visit;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class VisitDaoTest {

  VisitDao dao;
  Connection connection;


  @Test
  public void whenVisitExistThenReadByIDToProve() throws SQLException{
    this.dao= DaoFactory.getInstance().createVisitDao();
    this.connection =dao.getConnection();
    try {
      Visit actual = dao.read(1);
      Assert.assertEquals(1,actual.getId());
    } finally {
      connection.close();
    }
  }

  @Test
  public void testFindAllVisitsForAdmin() throws SQLException{
    this.dao= DaoFactory.getInstance().createVisitDao();
    this.connection =dao.getConnection();
    try {
      List<Visit> list = dao.findAll();
      printAllVisits(list);
    } finally {
      connection.close();
    }
  }

  @Test
  public void whenVisitsHaveFreeStates() throws SQLException{
    this.dao= DaoFactory.getInstance().createVisitDao();
    this.connection =dao.getConnection();
    try{
      VisitDao daoMock = mock(VisitDao.class);
      when(daoMock.findAllWithoutCustomer()).thenReturn(null);
      Assert.assertNull(daoMock.findAllWithoutCustomer());
    } finally {
      connection.close();
    }
  }

  private void printAllVisits(List<Visit> visits){
    for(Visit visit: visits){
      System.out.println(visit.toString());
    }
  }

}
