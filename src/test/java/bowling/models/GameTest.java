package bowling.models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GameTest {

    private static final int LAST_FRAME = 10;
    private static final int FIRST_FRAME = 1;

    @Test
    public void shouldReturnANewFrameWhenTheCurrentOneHasFinished() {
        final Roll roll = Roll.builder().pinsHit(10).build();
        final Frame frame = Frame.builder().rolls(Collections.singletonList(roll)).num(1).build();
        final Game game = Game.builder().frames(Collections.singletonList(frame)).build();

        final Frame expectedFrame = Frame.builder().num(2).game(game).build();

        assertThat(game.getLatestFrame(), is(expectedFrame));
    }

    @Test
    public void shouldNotBeFinishedWhenTheLastFrameIsAStrikeAndHasOnlyOneRoll() {
        final Roll roll = Roll.builder().pinsHit(10).build();

        final Game game = Game.builder().frames(new ArrayList<>()).build();

        for (int frameNumber = FIRST_FRAME; frameNumber <= LAST_FRAME; frameNumber++) {
            game.getFrames().add(Frame.builder().num(frameNumber).rolls(Arrays.asList(roll)).build());
        }

        assertThat(game.hasFinished(), is(false));
    }

    @Test
    public void shouldNotBeFinishedWhenTheLastFrameIsAStrikeAndHasTwoRolls() {
        final Roll roll = Roll.builder().pinsHit(10).build();

        final Game game = Game.builder().frames(new ArrayList<>()).build();

        for (int frameNumber = FIRST_FRAME; frameNumber < LAST_FRAME; frameNumber++) {
            game.getFrames().add(Frame.builder().num(frameNumber).rolls(Arrays.asList(roll)).build());
        }

        game.getFrames().add(Frame.builder().num(LAST_FRAME).rolls(Arrays.asList(roll, roll)).build());

        assertThat(game.hasFinished(), is(false));
    }

    @Test
    public void shouldNotBeFinishedWhenTheLastFrameIsASpareAndHasOnlyOneRoll() {
        final Roll roll = Roll.builder().pinsHit(10).build();

        final Game game = Game.builder().frames(new ArrayList<>()).build();

        for (int frameNumber = FIRST_FRAME; frameNumber <= LAST_FRAME; frameNumber++) {
            game.getFrames().add(Frame.builder().num(frameNumber).rolls(Arrays.asList(roll)).build());
        }

        assertThat(game.hasFinished(), is(false));
    }

    @Test
    public void shouldNotBeFinishedWhenTheLastFrameIsASpareAndHasTwoRolls() {
        final Roll roll = Roll.builder().pinsHit(10).build();

        final Game game = Game.builder().frames(new ArrayList<>()).build();

        for (int frameNumber = FIRST_FRAME; frameNumber < LAST_FRAME; frameNumber++) {
            game.getFrames().add(Frame.builder().num(frameNumber).rolls(Arrays.asList(roll)).build());
        }

        final Roll roll2 = Roll.builder().pinsHit(5).build();
        final Roll roll3 = Roll.builder().pinsHit(5).build();

        game.getFrames().add(Frame.builder().num(LAST_FRAME).rolls(Arrays.asList(roll2, roll3)).build());

        assertThat(game.hasFinished(), is(false));
    }


    @Test
    public void shouldNotBeFinishedWhenTheLastFrameIsIncomplete() {
        final Roll roll = Roll.builder().pinsHit(10).build();

        final Game game = Game.builder().frames(new ArrayList<>()).build();

        for (int frameNumber = FIRST_FRAME; frameNumber < LAST_FRAME; frameNumber++) {
            game.getFrames().add(Frame.builder().num(frameNumber).rolls(Arrays.asList(roll)).build());
        }

        final Roll roll2 = Roll.builder().pinsHit(5).build();
        game.getFrames().add(Frame.builder().num(LAST_FRAME).rolls(Arrays.asList(roll2)).build());

        assertThat(game.hasFinished(), is(false));
    }

    @Test
    public void shouldBeFinishedWhenTheLastFrameIsARegularOne() {
        final Roll roll1 = Roll.builder().pinsHit(10).build();
        final Game game = Game.builder().frames(new ArrayList<>()).build();

        for (int frameNumber = FIRST_FRAME; frameNumber < LAST_FRAME; frameNumber++) {
            game.getFrames().add(Frame.builder().num(frameNumber).rolls(Arrays.asList(roll1)).build());
        }

        final Roll roll2 = Roll.builder().pinsHit(3).build();
        final Roll roll3 = Roll.builder().pinsHit(3).build();
        final Frame frame2 = Frame.builder().num(LAST_FRAME).rolls(Arrays.asList(roll2, roll3)).build();

        game.getFrames().add(frame2);

        assertThat(game.hasFinished(), is(true));
    }

    @Test
    public void shouldNotBeFinishedWhenTheLastFrameIsARegularOne() {
        final Roll roll1 = Roll.builder().pinsHit(10).build();
        final Game game = Game.builder().frames(new ArrayList<>()).build();

        for (int frameNumber = FIRST_FRAME; frameNumber < LAST_FRAME; frameNumber++) {
            game.getFrames().add(Frame.builder().num(frameNumber).rolls(Arrays.asList(roll1)).build());
        }

        assertThat(game.hasFinished(), is(false));
    }
}