package bowling.repositories;

import bowling.models.Roll;
import org.springframework.data.repository.CrudRepository;

public interface RollRepository extends CrudRepository<Roll, Long> {
}
