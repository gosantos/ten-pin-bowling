package bowling.repositories;

import bowling.models.Frame;
import bowling.models.Row;
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
public class RowRepositoryTest {

    @Autowired
    private RowRepository rowRepository;

    @Test
    public void shouldSaveAndFetchARow() {
        final Row row = Row.builder().pinsHit(10).build();
        rowRepository.save(row);

        final Optional<Row> rowRepositoryById = rowRepository.findById(row.getId());

        assertThat(rowRepositoryById, is(Optional.of(row)));
    }

    @After
    public void tearDown() {
        rowRepository.deleteAll();
    }
}