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
        int rollNumber = 0;

        final HashMap<Integer, Integer> frameScores = new HashMap<>();

        for (int frameNumber = FIRST_FRAME; frameNumber <= numberOfFrames; frameNumber++) {
            score = score + getRollScore(rollNumber);
            rollNumber = incrementRollNumber(rollNumber);

            frameScores.put(frameNumber, score);
        }

        return frameScores;
    }

    private int getRollScore(int rollNumber) {
        if (isStrike(rollNumber)) {
            return ALL_PINS + strikeBonus(rollNumber);
        } else if (isSpare(rollNumber)) {
            return ALL_PINS + spareBonus(rollNumber);
        }

        return sumOfRolls(rollNumber);
    }

    private int incrementRollNumber(int rollNumber) {
        if (isStrike(rollNumber)) {
            return rollNumber + 1;
        }

        return rollNumber + 2;
    }

    private boolean isStrike(int rollNumber) {
        return get(rolls, rollNumber, 0) == ALL_PINS;
    }

    private boolean isSpare(int rollNumber) {
        return sumOfRolls(rollNumber) == ALL_PINS;
    }

    private int strikeBonus(int rollNumber) {
        return sumOfRolls(rollNumber + 1);
    }

    private int spareBonus(int rollNumber) {
        return get(rolls, rollNumber + 2, 0);
    }

    private int sumOfRolls(int rollNumber) {
        return get(rolls, rollNumber, 0) + get(rolls, rollNumber + 1, 0);
    }
}

