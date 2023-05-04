package udemy.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import udemy.spring.model.Vet;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
