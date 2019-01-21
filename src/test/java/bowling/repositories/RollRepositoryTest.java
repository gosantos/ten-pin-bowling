package bowling.repositories;

import bowling.models.Roll;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RollRepositoryTest {

    @Autowired
    private RollRepository rollRepository;

    @Test
    public void shouldSaveAndFetchARow() {
        final Roll roll = Roll.builder().pinsHit(10).build();
        rollRepository.save(roll);

        final Optional<Roll> rowRepositoryById = rollRepository.findById(roll.getId());

        assertThat(rowRepositoryById, is(Optional.of(roll)));
    }

    @After
    public void tearDown() {
        rollRepository.deleteAll();
    }
}