package bowling.models;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FrameScore {
    private Integer frameNumber;
    private Integer currentScore;
}
