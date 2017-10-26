package sbk.db1;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(schema = "SERVICES", name = "SYSTEM_PROPERTY")
public class SystemProperty
{
  @Id
  @Column(name = "CODE")
  @Size(max = 100)
  private String code;

  @Column(name = "VALUE")
  @Size(max = 1000)
  private String value;

  public String getCode()
  {
    return code;
  }

  public String getValue()
  {
    return value;
  }

  public void setCode(String code)
  {
    this.code = code;
  }

  public void setValue(String value)
  {
    this.value = value;
  }

  @Override public String toString()
  {
    return "SystemProperty{" +
        "code='" + code + '\'' +
        ", value='" + value + '\'' +
        '}';
  }
}
