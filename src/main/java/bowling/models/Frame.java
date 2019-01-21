package bowling.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Frame {
    private static final int TEN = 10;
    private static final int DEFAULT_VALUE = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "frame")
    private Set<Row> rows;

    @ManyToOne
    @JoinColumn(name="game_id")
    @JsonIgnore
    private Game game;

    public boolean isStrike() {
        return rows.stream().findFirst().map(row -> row.getPinsHit().equals(TEN)).orElse(false);
    }

    public boolean isSpare() {
        return !isStrike() && rows.stream().map(Row::getPinsHit).reduce(DEFAULT_VALUE, Integer::sum).equals(TEN);
    }
}
