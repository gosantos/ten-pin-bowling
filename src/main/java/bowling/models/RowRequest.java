package bowling.models;

import lombok.Value;

@Value
public class RowRequest {
    private Long rowId;
    private Integer pinsHit;
}
