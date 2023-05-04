package udemy.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import udemy.spring.model.Pet;
import udemy.spring.model.Specialty;

public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {
}
