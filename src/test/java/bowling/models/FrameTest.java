package bowling.models;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FrameTest {

    @Test
    public void isAStrike() {
        final Roll roll = Roll.builder().pinsHit(10).build();
        final HashSet<Roll> rolls = Sets.newHashSet(roll);
        final Frame frame = Frame.builder().rolls(rolls).build();

        assertThat(frame.isStrike(), is(true));
    }

    @Test
    public void isNotAStrike() {
        final Roll roll1 = Roll.builder().pinsHit(7).build();
        final Roll roll2 = Roll.builder().pinsHit(3).build();
        final List<Roll> rolls = Arrays.asList(roll1, roll2);
        final Frame frame = Frame.builder().rolls(rolls).build();

        assertThat(frame.isStrike(), is(false));
    }

    @Test
    public void isSpare() {
        final Roll roll1 = Roll.builder().pinsHit(7).build();
        final Roll roll2 = Roll.builder().pinsHit(3).build();
        final HashSet<Roll> rolls = Sets.newHashSet(roll1, roll2);
        final Frame frame = Frame.builder().rolls(rolls).build();

        assertThat(frame.isSpare(), is(true));
    }

    @Test
    public void isSpare2() {
        final Roll roll1 = Roll.builder().pinsHit(0).build();
        final Roll roll2 = Roll.builder().pinsHit(10).build();
        final HashSet<Roll> rolls = Sets.newHashSet(roll1, roll2);
        final Frame frame = Frame.builder().rolls(rolls).build();

        assertThat(frame.isSpare(), is(true));
    }

    @Test
    public void isNotSpare() {
        final Roll roll1 = Roll.builder().pinsHit(7).build();
        final Roll roll2 = Roll.builder().pinsHit(1).build();
        final HashSet<Roll> rolls = Sets.newHashSet(roll1, roll2);
        final Frame frame = Frame.builder().rolls(rolls).build();

        assertThat(frame.isSpare(), is(false));
    }

    @Test
    public void hasFinished() {
        final Roll roll1 = Roll.builder().pinsHit(7).build();
        final Roll roll2 = Roll.builder().pinsHit(1).build();
        final HashSet<Roll> rolls = Sets.newHashSet(roll1, roll2);
        final Frame frame = Frame.builder().rolls(rolls).build();

        assertThat(frame.hasFinished(), is(true));
    }

    @Test
    public void hasFinished2() {
        final Roll roll1 = Roll.builder().pinsHit(10).build();
        final HashSet<Roll> rolls = Sets.newHashSet(roll1);
        final Frame frame = Frame.builder().rolls(rolls).build();

        assertThat(frame.hasFinished(), is(true));
    }

    @Test
    public void hasNotFinished() {
        final Roll roll1 = Roll.builder().pinsHit(7).build();
        final HashSet<Roll> rolls = Sets.newHashSet(roll1);
        final Frame frame = Frame.builder().rolls(rolls).build();

        assertThat(frame.hasFinished(), is(false));
    }

    @Test
    public void hasNotFinished2() {
        final Frame frame = Frame.builder().rolls(new ArrayList<>()).build();

        assertThat(frame.hasFinished(), is(false));
    }

    @Test
    public void isValid() {
        final Roll roll1 = Roll.builder().pinsHit(7).build();
        final List<Roll> rolls = Collections.singletonList(roll1);
        final Frame frame = Frame.builder().rolls(rolls).build();

        assertThat(frame.isValid(), is(true));
    }


    @Test
    public void isValidWhenIsABonusRoll() {
        final Roll roll1 = Roll.builder().pinsHit(10).build();
        final Roll roll2 = Roll.builder().pinsHit(10).build();
        final List<Roll> rolls = Arrays.asList(roll1, roll2);
        final Frame frame = Frame.builder().rolls(rolls).build();

        assertThat(frame.isValid(), is(true));
    }

    @Test
    public void isNotAValidBonusRoll() {
        final Roll roll1 = Roll.builder().pinsHit(10).build();
        final Roll roll2 = Roll.builder().pinsHit(10).build();
        final Roll roll3 = Roll.builder().pinsHit(10).build();
        final Roll roll4 = Roll.builder().pinsHit(10).build();
        final List<Roll> rolls = Arrays.asList(roll1, roll2, roll3, roll4);
        final Frame frame = Frame.builder().rolls(rolls).build();

        assertThat(frame.isValid(), is(false));
    }

    @Test
    public void isNotValidWhenPinsAreGreaterThan10AndIsNotABonusRoll() {
        final Roll roll1 = Roll.builder().pinsHit(7).build();
        final Roll roll2 = Roll.builder().pinsHit(7).build();
        final List<Roll> rolls = Arrays.asList(roll1, roll2);
        final Frame frame = Frame.builder().rolls(rolls).build();

        assertThat(frame.isValid(), is(false));
    }
}