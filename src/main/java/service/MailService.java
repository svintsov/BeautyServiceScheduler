package service;

import bundle.MessageManager;
import dao.DaoFactory;
import dao.UserDao;
import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Main for mail function
 */
public class MailService {

  /**
   * Sends invitation to write review to user's mail
   * Now it works as a mock with logger
   * @param idUser
   * @throws SQLException
   */
    public void sendMailToUser(final int idUser) throws SQLException {
        final UserDao userDao = DaoFactory.getInstance().createUserDao();
        final Connection connection = userDao.getConnection();

        connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
        connection.setAutoCommit(false);

        final User user = userDao.read(idUser);

        if (user.getId()!=-1){
            writeMessageToLogger(user.getEmail());
        } else throw new SQLException();
    }

    private void writeMessageToLogger(final String mail){
        final Logger logger = LogManager.getRootLogger();

        logger.info(MessageManager.getProperty("message.mail") + mail);
    }
}
