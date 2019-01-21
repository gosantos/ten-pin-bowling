package bowling.repositories;

import bowling.models.Frame;
import org.springframework.data.repository.CrudRepository;

public interface FrameRepository extends CrudRepository<Frame, Long> {
}
