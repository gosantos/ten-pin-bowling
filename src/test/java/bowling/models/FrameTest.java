package bowling.models;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FrameTest {
    @Test
    public void shouldReturnTrueWhenIsAStrike() {
        final Roll roll = Roll.builder().pinsHit(10).build();
        final List<Roll> rolls = Arrays.asList(roll);
        final Frame frame = Frame.builder().rolls(rolls).build();

        assertThat(frame.isStrike(), is(true));
    }

    @Test
    public void shouldReturnFalseWhenIsNotAStrike() {
        final Roll roll1 = Roll.builder().pinsHit(7).build();
        final Roll roll2 = Roll.builder().pinsHit(3).build();
        final List<Roll> rolls = Arrays.asList(roll1, roll2);
        final Frame frame = Frame.builder().rolls(rolls).build();

        assertThat(frame.isStrike(), is(false));
    }

    @Test
    public void shouldReturnTrueWhenIsAStrikeOnTheBonusRoll() {
        final Roll roll = Roll.builder().pinsHit(10).build();
        final Roll roll2 = Roll.builder().pinsHit(10).build();
        final List<Roll> rolls = Arrays.asList(roll, roll2);
        final Frame frame = Frame.builder().num(10).rolls(rolls).build();

        assertThat(frame.isStrike(), is(true));
    }

    @Test
    public void shouldReturnTrueWhenIsASpare() {
        final Roll roll1 = Roll.builder().pinsHit(7).build();
        final Roll roll2 = Roll.builder().pinsHit(3).build();
        final List<Roll> rolls = Arrays.asList(roll1, roll2);
        ;
        final Frame frame = Frame.builder().rolls(rolls).build();

        assertThat(frame.isSpare(), is(true));
    }

    @Test
    public void shouldReturnTrueWhenIsASpareOnTheBonusRoll() {
        final Roll roll1 = Roll.builder().pinsHit(0).build();
        final Roll roll2 = Roll.builder().pinsHit(10).build();
        final List<Roll> rolls = Arrays.asList(roll1, roll2);
        final Frame frame = Frame.builder().num(10).rolls(rolls).build();

        assertThat(frame.isSpare(), is(true));
    }

    @Test
    public void shouldReturnFalseWhenIsNotASpare() {
        final Roll roll1 = Roll.builder().pinsHit(7).build();
        final Roll roll2 = Roll.builder().pinsHit(1).build();
        final List<Roll> rolls = Arrays.asList(roll1, roll2);
        final Frame frame = Frame.builder().rolls(rolls).build();

        assertThat(frame.isSpare(), is(false));
    }

    @Test
    public void shouldReturnTrueWhenARegularFrameHasFinished() {
        final Roll roll1 = Roll.builder().pinsHit(7).build();
        final Roll roll2 = Roll.builder().pinsHit(1).build();
        final List<Roll> rolls = Arrays.asList(roll1, roll2);
        final Frame frame = Frame.builder().rolls(rolls).num(1).build();

        assertThat(frame.hasFinished(), is(true));
    }

    @Test
    public void shouldBeFinishedWhenIsAStrike() {
        final Roll roll1 = Roll.builder().pinsHit(10).build();
        final List<Roll> rolls = Arrays.asList(roll1);
        final Frame frame = Frame.builder().rolls(rolls).num(1).build();

        assertThat(frame.hasFinished(), is(true));
    }

    @Test
    public void shouldNotBeFinishedWhenIsAStrikeOnTheBonusRoll() {
        final Roll roll1 = Roll.builder().pinsHit(10).build();
        final List<Roll> rolls = Arrays.asList(roll1);
        final Frame frame = Frame.builder().num(10).rolls(rolls).build();

        assertThat(frame.hasFinished(), is(false));
    }

    @Test
    public void shouldNotBeFinishedWhenIsAStrikeOnTheBonusRoll2() {
        final Roll roll1 = Roll.builder().pinsHit(10).build();
        final Roll roll2 = Roll.builder().pinsHit(10).build();
        final List<Roll> rolls = Arrays.asList(roll1, roll2);
        final Frame frame = Frame.builder().num(10).rolls(rolls).build();

        assertThat(frame.hasFinished(), is(false));
    }

    @Test
    public void shouldNotBeFinishedWhenIsASpareOnTheBonusRoll() {
        final Roll roll1 = Roll.builder().pinsHit(8).build();
        final Roll roll2 = Roll.builder().pinsHit(2).build();
        final List<Roll> rolls = Arrays.asList(roll1, roll2);
        final Frame frame = Frame.builder().num(10).rolls(rolls).build();

        assertThat(frame.hasFinished(), is(false));
    }

    @Test
    public void shouldBeFinishedWhenIsNotASpareOrStrikeOnTheBonusRoll() {
        final Roll roll1 = Roll.builder().pinsHit(7).build();
        final Roll roll2 = Roll.builder().pinsHit(2).build();
        final List<Roll> rolls = Arrays.asList(roll1, roll2);
        final Frame frame = Frame.builder().num(10).rolls(rolls).build();

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