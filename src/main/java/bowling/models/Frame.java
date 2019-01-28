package bowling.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Frame {
    private static final int MAX_PINS = 10;
    private static final int MIN_PINS = 0;
    private static final int REGULAR_ROLLS_SIZE = 2;
    private static final int BONUS_ROLLS_SIZE = 3;
    private static final int MAX_BONUS_PINS = 30;
    private static final int LAST_FRAME = 10;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private int number;

    @OneToMany(mappedBy = "frame")
    @Builder.Default
    private List<Roll> rolls = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id")
    private Game game;

    boolean hasFinished() {
        if (isBonusRoll()) {
            return rolls.size() == 3;
        }

        return isStrike() || rolls.size() == 2;
    }

    private boolean isStrike() {
        return rolls.stream().findFirst().map(row -> row.getPinsHit().equals(MAX_PINS)).orElse(false);
    }

    private boolean isSpare() {
        return !isStrike() && sumAllRolls().equals(MAX_PINS);
    }

    private boolean isBonusRoll() {
        return (isStrike() || isSpare()) && (number == LAST_FRAME);
    }

    private Integer sumAllRolls() {
        return rolls.stream().map(Roll::getPinsHit).reduce(MIN_PINS, Integer::sum);
    }
}
