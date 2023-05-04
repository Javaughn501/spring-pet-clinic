package udemy.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import udemy.spring.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Owner findByLastName(String lastName);
}
