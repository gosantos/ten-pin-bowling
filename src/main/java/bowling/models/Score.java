package bowling.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Iterables;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class Score {
    private Long gameId;

    @JsonIgnore
    private List<Frame> frames;

    @JsonProperty
    Integer getTotalScore() {
        int score = 0;
        int roll = 0;

        for (int frameNumber = 0; frameNumber < frames.size(); frameNumber++) {
            final Frame frame = Iterables.get(frames, frameNumber);

            if (frame.isStrike()) {
                score += 10 + sumStrikeBonus(roll);
                roll++;
            } else if (frame.isSpare()) {
                score += 10 + sumSpareBonus(roll);
                roll = roll + 2;
            } else {
                score += frame.sumAllRolls();
                roll = roll + 2;
            }
        }

        return score;
    }

    @JsonIgnore
    private List<Integer> getRolls() {
        return frames.stream().map(Frame::getRolls).flatMap(Collection::stream).map(Roll::getPinsHit).collect(Collectors.toList());
    }

    @JsonIgnore
    private Integer getPinsByRollNumber(int rollNumber) {
        return Iterables.get(getRolls(), rollNumber, 0);
    }

    private int sumStrikeBonus(int frameNumber) {
        return getPinsByRollNumber(frameNumber + 1) + getPinsByRollNumber(frameNumber + 2);
    }

    private int sumSpareBonus(int frameNumber) {
        return getPinsByRollNumber(frameNumber + 2);
    }
}

