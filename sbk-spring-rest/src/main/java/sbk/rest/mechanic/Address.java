package sbk.rest.mechanic;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address implements Serializable
{
  @Id
  @GeneratedValue
  private Long id;

  @Column
  private String addressLine1;

  @Column
  private String townSuburb;

  @Column
  private String postCode;

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public String getAddressLine1()
  {
    return addressLine1;
  }

  public void setAddressLine1(String addressLine1)
  {
    this.addressLine1 = addressLine1;
  }

  public String getTownSuburb()
  {
    return townSuburb;
  }

  public void setTownSuburb(String townSuburb)
  {
    this.townSuburb = townSuburb;
  }

  public String getPostCode()
  {
    return postCode;
  }

  public void setPostCode(String postCode)
  {
    this.postCode = postCode;
  }
}
