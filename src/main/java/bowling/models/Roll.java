package bowling.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Roll {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Min(value = 0)
    @Max(value = 10)
    private Integer pinsHit;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "frame_id")
    @ToString.Exclude
    @JsonIgnore
    private Frame frame;
}
