package bowling.controllers;

import bowling.models.Row;
import bowling.models.RowRequest;
import bowling.repositories.RowRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;


public class RowControllerTest {

    private RowController rowController;

    @Mock
    private RowRepository rowRepository;

    @Before
    public void setUp() {
        initMocks(this);
        rowController = new RowController(rowRepository);
    }

    @Test
    public void shouldSaveARow() {
        final RowRequest rowRequest = new RowRequest(1L, 10);
        final Row row = new Row(1L, 10);

        given(rowRepository.save(row)).willReturn(row);

        final ResponseEntity<Row> rowResponseEntity = rowController.save(rowRequest);

        assertThat(rowResponseEntity, is(ResponseEntity.ok(row)));
    }
}