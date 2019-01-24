package bowling.models;

import com.google.common.collect.Iterables;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Score {
    private static final int FIRST_FRAME = 1;
    private static final int ALL_PINS = 10;

    private Long gameId;

    private int numberOfFrames;

    @Builder.Default
    private List<Integer> rolls = new ArrayList<>();

    @Builder.Default
    private List<ScoreFrame> scoreFrames = new ArrayList<>();

    private int totalScore;

    public void calculateScore() {
        int score = 0;
        int roll = 0;

        List<ScoreFrame> scoreFrames = new ArrayList<>();

        for (int frameNumber = FIRST_FRAME; frameNumber <= numberOfFrames; frameNumber++) {
            if (isStrike(roll)) {
                score += ALL_PINS + strikeBonus(roll);
                roll++;
            } else if (isSpare(roll)) {
                score += ALL_PINS + spareBonus(roll);
                roll += 2;
            } else {
                score += sumOfRolls(roll);
                roll += 2;
            }

            final ScoreFrame scoreFrame = ScoreFrame.builder().frameNumber(frameNumber).currentScore(score).build();
            scoreFrames.add(scoreFrame);
        }

        setTotalScore(score);
        setScoreFrames(scoreFrames);
    }

    private boolean isStrike(int frame) {
        return Iterables.get(rolls, frame, 0) == 10;
    }

    private boolean isSpare(int frame) {
        return sumOfRolls(frame) == 10;
    }

    private int strikeBonus(int frame) {
        return sumOfRolls(frame + 1);
    }

    private int spareBonus(int frame) {
        return Iterables.get(rolls, frame + 2, 0);
    }

    private int sumOfRolls(int frame) {
        return Iterables.get(rolls, frame, 0) + Iterables.get(rolls, frame + 1, 0);
    }
}

