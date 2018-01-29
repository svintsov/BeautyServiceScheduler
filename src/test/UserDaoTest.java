import dao.DaoFactory;
import dao.UserDao;
import entity.Role;
import entity.User;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UserDaoTest {

  private UserDao dao;
  private Connection connection;

  @Rule
  public final ExpectedException exception = ExpectedException.none();


  @Test
  public void whenUserIsExistThenCatchSQLException() throws SQLException{
    final User user = new User("login_test", "password_test", "email_test", "full_name_test",
        Role.CUSTOMER);
    this.dao = DaoFactory.getInstance().createUserDao();
    this.connection = dao.getConnection();
    connection.setAutoCommit(false);
    try{
      dao.create(user);
      exception.expect(SQLException.class);
      dao.create(user);
    } finally {
      connection.rollback();
      connection.close();
    }
  }

  @Test
  public void whenUserNotExistThenReadByLoginToProve() throws SQLException{
    this.dao = DaoFactory.getInstance().createUserDao();
    this.connection = dao.getConnection();
    try {
      final User user = dao.read("login_test");
      Assert.assertEquals(-1,user.getId());
    } finally {
      connection.close();
    }
  }

}
