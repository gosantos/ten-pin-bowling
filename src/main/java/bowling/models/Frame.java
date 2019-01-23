package bowling.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.GenerationType.TABLE;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Frame {
    private static final int MAX_PINS = 10;
    private static final int MIN_PINS = 0;
    private static final int MAX_ROLLS_SIZE = 2;
    private static final int BONUS_ROLLS_SIZE = 3;
    private static final int MAX_BONUS_PINS = 30;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "frame")
    @Builder.Default
    private Collection<Roll> rolls = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id")
    private Game game;

    @JsonIgnore
    boolean isStrike() {
        return rolls.stream().findFirst().map(row -> row.getPinsHit().equals(MAX_PINS)).orElse(false);
    }

    @JsonIgnore
    boolean isSpare() {
        return !isStrike() && sumAllRolls().equals(MAX_PINS);
    }

    @AssertTrue
    @JsonIgnore
    boolean isValid() {
        return isBonusRoll() || (sumAllRolls() >= MIN_PINS && sumAllRolls() <= MAX_PINS);
    }

    Integer sumAllRolls() {
        return rolls.stream().map(Roll::getPinsHit).reduce(MIN_PINS, Integer::sum);
    }

    @JsonIgnore
    boolean isBonusRoll() {
        return (isStrike() || isSpare()) && (rolls.size() < BONUS_ROLLS_SIZE) && sumAllRolls() <= MAX_BONUS_PINS;
    }

    boolean hasFinished() {
        return isStrike() || (rolls.size() == MAX_ROLLS_SIZE);
    }
}
