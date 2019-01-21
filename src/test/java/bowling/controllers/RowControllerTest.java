package bowling.controllers;

import bowling.models.Frame;
import bowling.models.Row;
import bowling.models.RowRequest;
import bowling.repositories.FrameRepository;
import bowling.repositories.RowRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;


public class RowControllerTest {

    private RowController rowController;

    @Mock
    private RowRepository rowRepository;

    @Mock
    private FrameRepository frameRepository;

    @Before
    public void setUp() {
        initMocks(this);
        rowController = new RowController(rowRepository, frameRepository);
    }

    @Test
    public void shouldSaveARow() throws Exception {
        final RowRequest rowRequest = new RowRequest(1L, 10);

        final Frame frame = Frame.builder().build();
        final Optional<Frame> frameOptional = Optional.of(frame);
        given(frameRepository.findById(1L)).willReturn(frameOptional);

        final Row row = Row.builder().pinsHit(10).frame(frame).build();
        given(rowRepository.save(row)).willReturn(row);

        final Row savedRow = rowController.save(rowRequest);

        assertThat(savedRow, is(row));
    }
}