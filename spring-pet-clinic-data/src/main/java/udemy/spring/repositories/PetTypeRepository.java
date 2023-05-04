package udemy.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import udemy.spring.model.Pet;
import udemy.spring.model.PetType;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
