package bowling.services;

import bowling.exceptions.GameNotFoundException;
import bowling.models.Game;
import bowling.repositories.GameRepository;
import bowling.repositories.RollRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public class BowlingServiceTest {

    private BowlingService bowlingService;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private RollRepository rollRepository;

    @Before
    public void setUp() {
        initMocks(this);

        bowlingService = new BowlingService(gameRepository, rollRepository);
    }

    @Test
    public void givenAnExistingIdShouldReturnAGame() {
        final long gameId = 1L;
        final Game expectedGame = Game.builder().id(gameId).build();

        given(gameRepository.findById(gameId)).willReturn(Optional.of(expectedGame));

        final Game actualGame = bowlingService.findGameById(gameId);

        assertThat(actualGame, is(expectedGame));
    }

    @Test(expected = GameNotFoundException.class)
    public void givenANonExistingIdShouldReturnAGame() {
        bowlingService.findGameById(1L);
    }

    @Test
    public void shouldCreateANewGame() {
        final Game expectedGame = Game.builder().build();

        given(gameRepository.save(expectedGame)).willReturn(expectedGame);

        final Game actualGame = bowlingService.newGame();

        assertThat(actualGame, is(expectedGame));
    }
}