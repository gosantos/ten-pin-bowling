package bowling.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Iterables;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Game {
    private static final int MAX_FRAMES = 10;

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "game")
    @Builder.Default
    private Collection<Frame> frames = new ArrayList<>();

    @JsonIgnore
    public Frame getLatestFrame() {
        if (hasFinished()) {
            return null;
        }

        if (!frames.isEmpty() && !Iterables.getLast(frames).hasFinished()) {
            return Iterables.getLast(frames);
        }

        return Frame.builder().game(this).build();
    }

    boolean hasFinished() {
        return frames.size() == MAX_FRAMES && Iterables.getLast(frames).hasFinished();
    }
}
