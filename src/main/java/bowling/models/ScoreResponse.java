package bowling.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScoreResponse {
    private Long gameId;
    private List<FrameScore> scoreByFrame;
    private int totalScore;

}
