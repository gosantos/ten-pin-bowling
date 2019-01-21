package bowling.controllers;

import bowling.models.Frame;
import bowling.models.Game;
import bowling.models.Roll;
import bowling.models.RollRequest;
import bowling.repositories.GameRepository;
import bowling.repositories.RollRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;


public class BowlingControllerTest {

    private BowlingController bowlingController;

    @Mock
    private RollRepository rollRepository;

    @Mock
    private GameRepository gameRepository;

    @Before
    public void setUp() {
        initMocks(this);
        bowlingController = new BowlingController(rollRepository, gameRepository);
    }

    @Test
    public void shouldSaveARow() {
        final RollRequest rollRequest = new RollRequest(1L, 10);

        final Frame frame = Frame.builder().rolls(new ArrayList<>()).build();

        final Game game = Game.builder().frames(Collections.singletonList(frame)).build();

        given(gameRepository.findById(1L)).willReturn(Optional.of(game));

        final Roll roll = Roll.builder().pinsHit(10).frame(frame).build();
        given(rollRepository.save(roll)).willReturn(roll);

        final Roll savedRoll = bowlingController.save(rollRequest);

        assertThat(savedRoll, is(roll));
    }

    @Test
    public void shouldCreateANewGame() {
        final Game expectedNewGame = Game.builder().build();
        given(gameRepository.save(expectedNewGame)).willReturn(expectedNewGame);

        final Game actualNewGame = bowlingController.newGame();

        assertThat(actualNewGame, is(expectedNewGame));
    }
}