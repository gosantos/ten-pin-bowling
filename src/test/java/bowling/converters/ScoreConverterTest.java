package bowling.converters;

import bowling.models.Frame;
import bowling.models.Game;
import bowling.models.Roll;
import bowling.models.Score;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ScoreConverterTest {

    private static final int FIRST_FRAME = 1;
    private static final double LAST_FRAME = 10;
    private ScoreConverter scoreConverter;

    @Before
    public void setUp() throws Exception {
        scoreConverter = new ScoreConverter();
    }

    @Test
    public void shouldConvertFramesAndRollsToFlatList() {
        final Game game = Game.builder().build();

        for (int frameNumber = FIRST_FRAME; frameNumber < LAST_FRAME; frameNumber++) {
            final Roll roll = Roll.builder().pinsHit(10).build();
            game.getFrames().add(Frame.builder().num(frameNumber).rolls(Arrays.asList(roll)).build());
        }

        final Score score = scoreConverter.convert(game);

        score.getRolls().forEach(roll -> assertThat(roll, is(10)));
    }

    @Test
    public void shouldConvertWhenThereIsNoFrame() {
        final Score actualScore = scoreConverter.convert(Game.builder().id(1L).build());

        final Score expectedScore = Score.builder().gameId(1L).build();

        assertThat(actualScore, is(expectedScore));
    }
}
