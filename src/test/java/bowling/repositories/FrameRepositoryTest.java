package bowling.repositories;

import bowling.models.Frame;
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
public class FrameRepositoryTest {

    @Autowired
    private FrameRepository frameRepository;

    @Test
    public void shouldSaveAndFetchAFrame() {
        final Frame frame = Frame.builder().build();
        frameRepository.save(frame);

        final Optional<Frame> frameRepositoryById = frameRepository.findById(frame.getId());

        assertThat(frameRepositoryById, is(Optional.of(frame)));
    }

    @After
    public void tearDown() {
        frameRepository.deleteAll();
    }
}