package sbk.dbcptester;

import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

/**
 * Rewrite of {@link PI026SchedulerJob}.
 */
@Component
public class PI026SchedulerJobMk2
{

  private static final Logger logger = LoggerFactory.getLogger(PI026SchedulerJobMk2.class);
  public static final String SCHEDULER_JOB_NAME = "Schedule PI026";
  private static final String PI026_JOB_NAME = "PI026";
  private static final String PI026_NEXT_RUN_PROCEDURE_CALL = "{ ? = call next_run_time(?,?) }";

  @Autowired
  JdbcTemplate jdbcTemplate;

  // PI026 - 5:30pm on the 2nd business day of each month
  public GregorianCalendar getNextPI026RunDate(GregorianCalendar fromDate)
  {
    SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withFunctionName("next_run_time");
    Map<String, Object> params = new HashMap<>();
    params.put("p_date", new Timestamp(fromDate.getTime().getTime()));
    params.put("p_interval", "MONTH");

    Map<String, Object> results = call.execute(params);
    Timestamp nextRun = (Timestamp) results.get("returnvalue");
    logger.info("Result: {}", nextRun);

    GregorianCalendar nextRunDate = new GregorianCalendar();
    nextRunDate = new GregorianCalendar();
    nextRunDate.setTime(nextRun);
    return nextRunDate;
  }
}
