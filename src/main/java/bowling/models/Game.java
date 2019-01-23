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

import static javax.persistence.GenerationType.IDENTITY;

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
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "game")
    @JsonIgnore
    @Builder.Default
    private Collection<Frame> frames = new ArrayList<>();

    @JsonIgnore
    public Frame getCurrentFrame() {
        final Frame lastFrame = Iterables.getLast(frames, Frame.builder().num(1).game(this).build());

        if (lastFrame.hasFinished()) {
            return Frame.builder().num(lastFrame.getNum() + 1).game(this).build();
        }

        return lastFrame;
    }

    public boolean hasFinished() {
        return frames.size() == MAX_FRAMES && Iterables.getLast(frames).hasFinished();
    }
}
