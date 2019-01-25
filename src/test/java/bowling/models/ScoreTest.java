package bowling.models;

import bowling.converters.ScoreConverter;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ScoreTest {

    private ScoreConverter scoreConverter;

    @Before
    public void setUp() throws Exception {
        scoreConverter = new ScoreConverter();
    }

    @Test
    public void shouldGetTheTotalScore() {
        // 10 + 3 + 4
        final Roll roll1 = Roll.builder().pinsHit(10).build();
        final Frame frame1 = Frame.builder().rolls(Arrays.asList(roll1)).build();
        // 3 + 4
        final Roll roll2 = Roll.builder().pinsHit(3).build();
        final Roll roll3 = Roll.builder().pinsHit(4).build();
        final Frame frame2 = Frame.builder().rolls(Arrays.asList(roll2, roll3)).build();
        // 5 + 5 + 4
        final Roll roll4 = Roll.builder().pinsHit(5).build();
        final Roll roll5 = Roll.builder().pinsHit(5).build();
        final Frame frame3 = Frame.builder().rolls(Arrays.asList(roll4, roll5)).build();

        final Roll roll6 = Roll.builder().pinsHit(4).build();
        final Roll roll7 = Roll.builder().pinsHit(3).build();
        final Frame frame4 = Frame.builder().rolls(Arrays.asList(roll6, roll7)).build();

        final Roll roll8 = Roll.builder().pinsHit(10).build();
        final Frame frame5 = Frame.builder().rolls(Arrays.asList(roll8)).build();

        final Roll roll9 = Roll.builder().pinsHit(10).build();
        final Frame frame6 = Frame.builder().rolls(Arrays.asList(roll9)).build();

        final Roll roll10 = Roll.builder().pinsHit(10).build();
        final Frame frame7 = Frame.builder().rolls(Arrays.asList(roll10)).build();

        final Roll roll11 = Roll.builder().pinsHit(10).build();
        final Frame frame8 = Frame.builder().rolls(Arrays.asList(roll11)).build();

        final Roll roll12 = Roll.builder().pinsHit(10).build();
        final Frame frame9 = Frame.builder().rolls(Arrays.asList(roll12)).build();

        final Roll roll13 = Roll.builder().pinsHit(8).build();
        final Roll roll14 = Roll.builder().pinsHit(2).build();
        final Roll roll15 = Roll.builder().pinsHit(2).build();
        final Frame frame10 = Frame.builder().rolls(Arrays.asList(roll13, roll14, roll15)).build();

        final List<Frame> frames = Arrays.asList(frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10);

        final Game game = Game.builder().id(1L).frames(frames).build();

        final Score score = scoreConverter.convert(game);

        final Integer totalScore = score.calculateTotalScore();

        assertThat(totalScore, is(195));
    }

    @Test
    public void shouldGetTheTotalScoreWhenThereIsAStrikeOnTheLastFrame() {

        final List<Frame> frames = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            final Frame frame = Frame.builder().rolls(Arrays.asList(Roll.builder().pinsHit(10).build())).build();

            frames.add(frame);
        }

        final Roll roll = Roll.builder().pinsHit(10).build();
        final List<Roll> bonusRolls = Arrays.asList(roll, roll, roll);
        final Frame frame = Frame.builder().rolls(bonusRolls).build();

        frames.add(frame);

        final Game game = Game.builder().id(1L).frames(frames).build();

        final Score scoreResponse = scoreConverter.convert(game);

        final Integer totalScore = scoreResponse.calculateTotalScore();

        assertThat(totalScore, is(300));
    }

    @Test
    public void shouldGetTheTotalScoreWhenThereIsASpareOnTheLastFrame() {

        final List<Frame> frames = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            final Frame frame = Frame.builder().rolls(Arrays.asList(Roll.builder().pinsHit(10).build())).build();

            frames.add(frame);
        }

        final Roll roll = Roll.builder().pinsHit(5).build();
        final Roll roll2 = Roll.builder().pinsHit(5).build();
        final Roll roll3 = Roll.builder().pinsHit(3).build();
        final List<Roll> bonusRolls = Arrays.asList(roll, roll2, roll3);
        final Frame frame = Frame.builder().rolls(bonusRolls).build();

        frames.add(frame);

        final Game game = Game.builder().id(1L).frames(frames).build();

        final Score scoreResponse = scoreConverter.convert(game);

        final Integer totalScore = scoreResponse.calculateTotalScore();

        assertThat(totalScore, is(268));
    }
}