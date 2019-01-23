package bowling.controllers;

import bowling.models.Frame;
import bowling.models.Game;
import bowling.models.Roll;
import bowling.models.RollRequest;
import bowling.services.BowlingService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;


public class BowlingControllerTest {

    private BowlingController bowlingController;

    @Mock
    private BowlingService bowlingService;

    @Before
    public void setUp() {
        initMocks(this);
        bowlingController = new BowlingController(bowlingService);
    }

    @Test
    public void shouldSaveARow() {
        final RollRequest rollRequest = new RollRequest(10);

        final Frame frame = Frame.builder().rolls(new ArrayList<>()).build();

        final Game game = Game.builder().frames(Collections.singletonList(frame)).build();

        given(bowlingService.findGameById(1L)).willReturn(game);

        final Roll roll = Roll.builder().pinsHit(10).frame(frame).build();

        given(bowlingService.updateGame(game, rollRequest.getPinsHit())).willReturn(roll);

        final Roll savedRoll = bowlingController.updateGame(1L, rollRequest);

        assertThat(savedRoll, is(roll));
    }

    @Test
    public void shouldCreateANewGame() {
        final Game expectedNewGame = Game.builder().build();

        given(bowlingService.newGame()).willReturn(expectedNewGame);

        final Game actualNewGame = bowlingController.newGame();

        assertThat(actualNewGame, is(expectedNewGame));
    }
}