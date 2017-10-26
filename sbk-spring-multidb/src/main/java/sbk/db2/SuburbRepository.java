package sbk.db2;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by simonkahl on 25/10/17.
 */
@Repository
public interface SuburbRepository extends CrudRepository<Suburb, String>
{
}
