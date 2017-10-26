package sbk.db2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by simonkahl on 25/10/17.
 */
@Entity
@Table(name = "SUBURB", schema = "CORE")
public class Suburb
{
  @Id
  @Column
  private String suburbName;

  @Column
  private String saleStatisticsIndicator;

  public String getSuburbName()
  {
    return suburbName;
  }

  public void setSuburbName(String suburbName)
  {
    this.suburbName = suburbName;
  }

  public String getSaleStatisticsIndicator()
  {
    return saleStatisticsIndicator;
  }

  public void setSaleStatisticsIndicator(String saleStatisticsIndicator)
  {
    this.saleStatisticsIndicator = saleStatisticsIndicator;
  }

  @Override public String toString()
  {
    return "Suburb{" +
        "suburbName='" + suburbName + '\'' +
        ", saleStatisticsIndicator='" + saleStatisticsIndicator + '\'' +
        '}';
  }
}
