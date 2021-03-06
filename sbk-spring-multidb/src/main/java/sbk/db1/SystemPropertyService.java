package sbk.db1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Services for dealing with the property code table.
 */
@Service
public class SystemPropertyService
{
  @Autowired
  private SystemPropertyRepository systemPropertyRepository;

  @Transactional
  public List<SystemProperty> getAll()
  {
    return (List<SystemProperty>) systemPropertyRepository.findAll();
  }
}
