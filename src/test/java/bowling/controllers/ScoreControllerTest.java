package bowling.controllers;

import bowling.converters.ScoreConverter;
import bowling.exceptions.GameNotFoundException;
import bowling.models.Game;
import bowling.models.Score;
import bowling.models.ScoreResponse;
import bowling.repositories.GameRepository;
import bowling.services.BowlingService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import java.util.Collections;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.MockitoAnnotations.initMocks;

public class ScoreControllerTest {

    private ScoreController scoreController;

    @Mock
    private BowlingService bowlingService;

    @Mock
    private ScoreConverter scoreConverter;

    @Before
    public void setUp() {
        initMocks(this);
        scoreController = new ScoreController(bowlingService, scoreConverter);
    }

    @Test(expected = GameNotFoundException.class)
    public void shouldReturnNotFoundWhenGameIdDoesNotExist() {
        given(bowlingService.findGameById(100L)).willThrow(GameNotFoundException.class);

        scoreController.getScore(100L);
    }

    @Test
    public void shouldReturnAScoreWhenGameIdIsValid() {
        final Game game = Game.builder().id(100L).build();

        given(bowlingService.findGameById(100L)).willReturn(game);

        given(scoreConverter.convert(game)).willReturn(Score.builder().build());

        final ScoreResponse expectedScore = ScoreResponse.builder().gameId(100L).scoreByFrame(emptyList()).build();

        final ScoreResponse actualScore = scoreController.getScore(100L);

        assertThat(expectedScore, is(actualScore));
    }
}