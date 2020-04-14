package sbk.dbcptester;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Some problem code as lifted from an app.  Notice it does not close connection on success.
 */
@Component
public class PI026SchedulerJob
{

  private static final Logger logger = LoggerFactory.getLogger(PI026SchedulerJob.class);
  public static final String SCHEDULER_JOB_NAME = "Schedule PI026";
  private static final String PI026_JOB_NAME = "PI026";
  private static final String PI026_NEXT_RUN_PROCEDURE_CALL = "{ ? = call next_run_time(?,?) }";

  @Autowired
  JdbcTemplate jdbcTemplate;

  // PI026 - 5:30pm on the 2nd business day of each month
  public GregorianCalendar getNextPI026RunDate(GregorianCalendar fromDate)
  {
    GregorianCalendar nextRunDate = new GregorianCalendar();

    Connection connection = null;
    boolean transactionCompleted = false;

    CallableStatement callProc = null;

    try
    {
      connection = jdbcTemplate.getDataSource().getConnection();
      connection.setAutoCommit(false);

      logger.info("Call proc");
      callProc = connection.prepareCall(PI026_NEXT_RUN_PROCEDURE_CALL);

      callProc.registerOutParameter(1, Types.TIMESTAMP);
      callProc.setTimestamp(2, new Timestamp(fromDate.getTime().getTime()));
      callProc.setString(3, "MONTH");
      callProc.execute();
      Timestamp nextRun = callProc.getTimestamp(1);

      logger.info("Result: {}", nextRun);

      nextRunDate = new GregorianCalendar();
      nextRunDate.setTime(nextRun);

      transactionCompleted = true;
    }
    catch (SQLException e)
    {
      logger.error("That's bad", e);

      if (connection != null)
      {
        try
        {

          connection.rollback();
        }
        catch (SQLException e2)
        {
          logger.debug(e.getMessage());
          throw new RuntimeException(e);
        }
      }

      if (callProc != null)
      {
        try
        {
          callProc.close();
        }
        catch (SQLException sqle)
        {
          logger.debug(sqle.getMessage());
          throw new RuntimeException(sqle);
        }
      }
      if (connection != null)
      {
        if (transactionCompleted)
        {
          try
          {
            connection.commit();
          }
          catch (SQLException sqle)
          {
            logger.debug(sqle.getMessage());
            throw new RuntimeException(sqle);
          }
        }
        try
        {
          connection.close();
        }
        catch (SQLException sqle)
        {
          logger.debug(sqle.getMessage());
          throw new RuntimeException(sqle);
        }
      }
    }

    return nextRunDate;
  }
}
