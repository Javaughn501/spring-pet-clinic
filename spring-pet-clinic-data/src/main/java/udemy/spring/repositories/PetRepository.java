package udemy.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import udemy.spring.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
