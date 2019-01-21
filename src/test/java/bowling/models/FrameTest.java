package bowling.models;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.HashSet;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FrameTest {

    @Test
    public void isAStrike() {
        final Row row = Row.builder().pinsHit(10).build();
        final HashSet<Row> rows = Sets.newHashSet(row);
        final Frame frame = Frame.builder().rows(rows).build();

        assertThat(frame.isStrike(), is(true));
    }

    @Test
    public void isNotAStrike() {
        final Row row1 = Row.builder().pinsHit(7).build();
        final Row row2 = Row.builder().pinsHit(3).build();
        final HashSet<Row> rows = Sets.newHashSet(row1, row2);
        final Frame frame = Frame.builder().rows(rows).build();

        assertThat(frame.isStrike(), is(false));
    }

    @Test
    public void isSpare() {
        final Row row1 = Row.builder().pinsHit(7).build();
        final Row row2 = Row.builder().pinsHit(3).build();
        final HashSet<Row> rows = Sets.newHashSet(row1, row2);
        final Frame frame = Frame.builder().rows(rows).build();

        assertThat(frame.isSpare(), is(true));
    }

    @Test
    public void isSpare2() {
        final Row row1 = Row.builder().pinsHit(0).build();
        final Row row2 = Row.builder().pinsHit(10).build();
        final HashSet<Row> rows = Sets.newHashSet(row1, row2);
        final Frame frame = Frame.builder().rows(rows).build();

        assertThat(frame.isSpare(), is(true));
    }

    @Test
    public void isNotSpare() {
        final Row row1 = Row.builder().pinsHit(7).build();
        final Row row2 = Row.builder().pinsHit(1).build();
        final HashSet<Row> rows = Sets.newHashSet(row1, row2);
        final Frame frame = Frame.builder().rows(rows).build();

        assertThat(frame.isSpare(), is(false));
    }
}