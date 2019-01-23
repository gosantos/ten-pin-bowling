package bowling.controllers;

import bowling.repositories.GameRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

public class ScoreControllerTest {

    private ScoreController scoreController;

    @Mock
    private GameRepository gameRepository;

    @Before
    public void setUp() {
        initMocks(this);
        scoreController = new ScoreController(gameRepository);
    }

    @Test
    public void givenAValidGameIdShouldCalculateTheScore() throws NotFoundException {
//        assertThat(scoreController.getScore(1L), is(nullValue()));
    }
}