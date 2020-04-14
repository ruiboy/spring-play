package sbk.dbcptester;

import java.lang.management.ManagementFactory;
import java.util.GregorianCalendar;

import javax.management.JMX;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariPoolMXBean;

@Component
public class TestRunner implements ApplicationListener<ContextRefreshedEvent>
{
  private static final Logger logger = LoggerFactory.getLogger(TestRunner.class);

  private static HikariPoolMXBean poolProxy;

  @Autowired
  PI026SchedulerJob pi026SchedulerJob;

  @Autowired
  PI026SchedulerJobMk2 pi026SchedulerJobMk2;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
  {
    // hikari pool is lazy initialised when first used, so can't check connections until after

    for (int i = 0; i < 1000; i++)
    {
      sleep(1000);
      //      pi026SchedulerJob.getNextPI026RunDate(new GregorianCalendar());
      pi026SchedulerJobMk2.getNextPI026RunDate(new GregorianCalendar());
      trackConnections();
    }
  }

  private void sleep(long millis)
  {
    try
    {
      Thread.sleep(millis);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }

  private void trackConnections()
  {
    if (poolProxy == null)
    {
      try
      {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName poolName = new ObjectName("com.zaxxer.hikari:type=Pool (sbk)");
        poolProxy = JMX.newMXBeanProxy(mBeanServer, poolName, HikariPoolMXBean.class);
      }
      catch (Exception e)
      {
        logger.error("Could not get mbean", e);
      }
    }

    logger.info("Idle [{}], Active [{}], Total [{}]",
        poolProxy.getIdleConnections(),
        poolProxy.getActiveConnections(),
        poolProxy.getThreadsAwaitingConnection());
  }
}
