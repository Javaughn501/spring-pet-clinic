package udemy.spring.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import udemy.spring.model.Visit;
import udemy.spring.services.VisitService;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class VisitServiceMapImpl extends AbstractMapService<Visit, Long> implements VisitService  {
    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public Visit findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public Visit save(Visit object) {

        if (object.getPet() == null || object.getPet().getOwner() == null || object.getPet().getId() == null) {
            throw new RuntimeException("Invalid Visit");
        }

        return super.save(object);
    }

    @Override
    public void delete(Visit object) {
        super.deleteByObject(object);
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }
}
