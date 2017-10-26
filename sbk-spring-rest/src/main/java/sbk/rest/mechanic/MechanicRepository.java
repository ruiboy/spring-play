package sbk.rest.mechanic;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false)
public interface MechanicRepository extends CrudRepository<Mechanic, Long>
{
    @Override
    @RestResource(exported = false)
    void delete(Long id);
}
