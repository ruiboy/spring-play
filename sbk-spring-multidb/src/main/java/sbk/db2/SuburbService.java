package sbk.db2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by simonkahl on 25/10/17.
 */
@Service
public class SuburbService
{
  @Autowired
  SuburbRepository suburbRepository;

//  @Transactional("transactionManager2")
  public Iterable<Suburb> getAll() {
    return suburbRepository.findAll();
  }
}
