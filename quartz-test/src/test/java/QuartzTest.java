import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.quartz.CronExpression;

public class QuartzTest
{
  @Test
  public void thing1()
  {
    System.out.println("fart");


    printCronDetails("         0 0 0 ? 1,4,7,10 MON#2 *");
  }

  private void printCronDetails(String expr)
  {
    System.out.println("----");
    System.out.println(expr.trim());
    boolean valid = CronExpression.isValidExpression(expr);
    System.out.println("Valid: " + valid);

    if (valid)
    {
      try
      {
        CronExpression cron = new CronExpression(expr);
        //System.out.println("Summary:\n" + cron.getExpressionSummary());

        Date nvt = cron.getNextValidTimeAfter(
            new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse("05-04-2020 15:10:00"));
        System.out.println("First run: " + nvt);

        Date nvt1 = cron.getNextValidTimeAfter(nvt);
        System.out.println("Next  run: " + nvt1);

        Date nvt2 = cron.getNextValidTimeAfter(nvt1);
        System.out.println("Next  run: " + nvt2);
      }
      catch (ParseException e)
      {
        System.out.println("Well that was dumb");
      }
    }
  }
}
