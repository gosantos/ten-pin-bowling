package bowling.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RollRequest {
    private Long gameId;
    private Integer pinsHit;
}
