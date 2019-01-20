package bowling.repositories;

import bowling.models.Row;
import org.springframework.data.repository.CrudRepository;

public interface RowRepository extends CrudRepository<Row, Long> {
}
