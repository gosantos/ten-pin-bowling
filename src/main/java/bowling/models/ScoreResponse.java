package bowling.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScoreResponse {
    private Long gameId;
    private Map<Integer, Integer> scoreByFrame;
    private int totalScore;
}
