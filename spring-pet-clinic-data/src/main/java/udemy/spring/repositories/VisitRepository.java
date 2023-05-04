package udemy.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import udemy.spring.model.Pet;
import udemy.spring.model.Visit;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
