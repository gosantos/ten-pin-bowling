package bowling.controllers;

import bowling.exceptions.GameNotFoundException;
import bowling.models.Game;
import bowling.models.Score;
import bowling.repositories.GameRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
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

    @Test(expected = GameNotFoundException.class)
    public void shouldReturnNotFoundWhenGameIdDoesNotExist() {
        scoreController.getScore(100L);
    }

    @Test
    public void shouldReturnAScoreWhenGameIdIsValid() {
        final Game game = Game.builder().id(100L).build();

        given(gameRepository.findById(100L)).willReturn(Optional.of(game));

        final Score expectedScore = Score.builder().gameId(100L).frames(Collections.emptyList()).build();

        final Score actualScore = scoreController.getScore(100L);

        assertThat(expectedScore, is(actualScore));
    }
}