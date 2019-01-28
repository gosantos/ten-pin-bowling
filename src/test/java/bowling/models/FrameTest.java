package bowling.models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FrameTest {
    @Test
    public void shouldReturnTrueWhenARegularFrameHasFinished() {
        final Roll roll1 = Roll.builder().pinsHit(7).build();
        final Roll roll2 = Roll.builder().pinsHit(1).build();
        final List<Roll> rolls = Arrays.asList(roll1, roll2);
        final Frame frame = Frame.builder().rolls(rolls).number(1).build();

        assertThat(frame.hasFinished(), is(true));
    }

    @Test
    public void shouldBeFinishedWhenIsAStrike() {
        final Roll roll1 = Roll.builder().pinsHit(10).build();
        final List<Roll> rolls = Arrays.asList(roll1);
        final Frame frame = Frame.builder().rolls(rolls).number(1).build();

        assertThat(frame.hasFinished(), is(true));
    }

    @Test
    public void shouldNotBeFinishedWhenIsAStrikeOnTheBonusRoll() {
        final Roll roll1 = Roll.builder().pinsHit(10).build();
        final List<Roll> rolls = Arrays.asList(roll1);
        final Frame frame = Frame.builder().number(10).rolls(rolls).build();

        assertThat(frame.hasFinished(), is(false));
    }

    @Test
    public void shouldNotBeFinishedWhenIsAStrikeOnTheBonusRoll2() {
        final Roll roll1 = Roll.builder().pinsHit(10).build();
        final Roll roll2 = Roll.builder().pinsHit(10).build();
        final List<Roll> rolls = Arrays.asList(roll1, roll2);
        final Frame frame = Frame.builder().number(10).rolls(rolls).build();

        assertThat(frame.hasFinished(), is(false));
    }

    @Test
    public void shouldNotBeFinishedWhenIsASpareOnTheBonusRoll() {
        final Roll roll1 = Roll.builder().pinsHit(8).build();
        final Roll roll2 = Roll.builder().pinsHit(2).build();
        final List<Roll> rolls = Arrays.asList(roll1, roll2);
        final Frame frame = Frame.builder().number(10).rolls(rolls).build();

        assertThat(frame.hasFinished(), is(false));
    }

    @Test
    public void shouldBeFinishedWhenIsNotASpareOrStrikeOnTheBonusRoll() {
        final Roll roll1 = Roll.builder().pinsHit(7).build();
        final Roll roll2 = Roll.builder().pinsHit(2).build();
        final List<Roll> rolls = Arrays.asList(roll1, roll2);
        final Frame frame = Frame.builder().number(10).rolls(rolls).build();

        assertThat(frame.hasFinished(), is(true));
    }

    @Test
    public void shouldNotBeFinishedWhenIsNotAStrikeOrSpare() {
        final Roll roll1 = Roll.builder().pinsHit(7).build();
        final List<Roll> rolls = Arrays.asList((roll1));
        final Frame frame = Frame.builder().rolls(rolls).build();

        assertThat(frame.hasFinished(), is(false));
    }

    @Test
    public void shouldNotBeFinishedWhenThereIsNoRolls() {
        final Frame frame = Frame.builder().rolls(new ArrayList<>()).build();

        assertThat(frame.hasFinished(), is(false));
    }
}