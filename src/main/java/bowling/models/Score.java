package bowling.models;

import com.google.common.collect.Iterables;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Integer calculateTotalScore() {
        return getLast(calculateScoreByFrame().values(), 0);
    }

    public Map<Integer, Integer> calculateScoreByFrame() {
        int score = 0;
        int roll = 0;

        final HashMap<Integer, Integer> frameScores = new HashMap<>();

        for (int frameNumber = FIRST_FRAME; frameNumber <= numberOfFrames; frameNumber++) {
            score = score + getRollScore(roll);
            roll = incrementRollNumber(roll);

            frameScores.put(frameNumber, score);
        }

        return frameScores;
    }

    private int getRollScore(int roll) {
        if (isStrike(roll)) {
            return ALL_PINS + strikeBonus(roll);
        } else if (isSpare(roll)) {
            return ALL_PINS + spareBonus(roll);
        }

        return sumOfRolls(roll);
    }

    private int incrementRollNumber(int roll) {
        if (isStrike(roll)) {
            return roll + 1;
        }

        return roll + 2;
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

