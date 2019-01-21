package bowling.models;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GameTest {

    private static final int MAX_FRAMES = 10;

    @Test
    public void getLatestFrame() {
        final Roll roll = Roll.builder().pinsHit(10).build();
        final Frame frame = Frame.builder().rolls(Collections.singletonList(roll)).build();
        final Game game = Game.builder().frames(Collections.singletonList(frame)).build();

        final Frame expectedFrame = Frame.builder().game(game).build();

        assertThat(game.getLatestFrame(), is(expectedFrame));
    }

    @Test
    public void getLatestFrame2() {
        final Game game = Game.builder().build();

        final Frame expectedFrame = Frame.builder().game(game).build();

        assertThat(game.getLatestFrame(), is(expectedFrame));
    }

    @Test
    public void hasFinished() {
        final Roll roll = Roll.builder().pinsHit(10).build();

        final Game game = Game.builder().frames(Sets.newHashSet()).build();

        for (int i = 0; i < MAX_FRAMES; i++) {
            game.getFrames().add(Frame.builder().id((long) i).rolls(Sets.newHashSet(roll)).build());
        }

        assertThat(game.hasFinished(), is(true));
    }

    @Test
    public void hasFinished2() {
        final Roll roll1 = Roll.builder().pinsHit(10).build();
        final Game game = Game.builder().frames(Sets.newHashSet()).build();

        for (int i = 0; i < MAX_FRAMES - 1; i++) {
            game.getFrames().add(Frame.builder().id((long) i).rolls(Sets.newHashSet(roll1)).build());
        }

        final Roll roll2 = Roll.builder().pinsHit(3).build();
        final Frame frame2 = Frame.builder().rolls(Sets.newHashSet(roll2)).build();

        game.getFrames().add(frame2);

        assertThat(game.hasFinished(), is(true));
    }

    @Test
    public void hasNotFinished() {
        final Roll roll1 = Roll.builder().pinsHit(10).build();
        final Game game = Game.builder().frames(new ArrayList<>()).build();

        for (int i = 0; i < MAX_FRAMES - 1; i++) {
            game.getFrames().add(Frame.builder().id((long) i).rolls(Collections.singletonList(roll1)).build());
        }

        assertThat(game.hasFinished(), is(false));
    }

    @Test
    public void hasNotFinished2() {
        final Game game = Game.builder().frames(new ArrayList<>()).build();

        for (int i = 0; i < MAX_FRAMES - 1; i++) {
            final Roll roll = Roll.builder().pinsHit(10).build();
            game.getFrames().add(Frame.builder().id((long) i).rolls(Collections.singletonList(roll)).build());
        }

        final Roll roll2 = Roll.builder().pinsHit(3).build();
        final Frame frame2 = Frame.builder().rolls(Collections.singletonList(roll2)).build();

        game.getFrames().add(frame2);

        assertThat(game.hasFinished(), is(false));
    }
}