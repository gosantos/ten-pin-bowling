package bowling.models;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Iterables.get;
import static com.google.common.collect.Iterables.getLast;

@Data
@Builder
public class Score {
    private static final int FIRST_FRAME = 1;
    private static final int ALL_PINS = 10;

    private int numberOfFrames;

    @Builder.Default
    private List<Integer> rolls = new ArrayList<>();

    public List<FrameScore> calculateScoreByFrame() {
        int score = 0;
        int roll = 0;

        List<FrameScore> frameScores = new ArrayList<>();

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

            final FrameScore frameScore = FrameScore.builder().frameNumber(frameNumber).currentScore(score).build();
            frameScores.add(frameScore);
        }

        return frameScores;
    }

    public Integer calculateTotalScore() {
        if (calculateScoreByFrame().isEmpty()) {
            return 0;
        }

        return getLast(calculateScoreByFrame()).getCurrentScore();
    }

    private boolean isStrike(int roll) {
        return get(rolls, roll, 0) == ALL_PINS;
    }

    private boolean isSpare(int roll) {
        return sumOfRolls(roll) == ALL_PINS;
    }

    private int strikeBonus(int roll) {
        return sumOfRolls(roll + 1);
    }

    private int spareBonus(int roll) {
        return get(rolls, roll + 2, 0);
    }

    private int sumOfRolls(int roll) {
        return get(rolls, roll, 0) + get(rolls, roll + 1, 0);
    }
}

