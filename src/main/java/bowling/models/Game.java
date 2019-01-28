package bowling.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Iterables.getLast;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Game {
    private static final int MAX_FRAMES = 10;
    private static final int FIRST_FRAME = 1;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "game")
    @JsonIgnore
    @Builder.Default
    private List<Frame> frames = new ArrayList<>();

    @JsonIgnore
    public Frame getCurrentFrame() {
        final Frame lastFrame = getLast(frames, createNewFrame(FIRST_FRAME));

        if (lastFrame.hasFinished()) {
            return createNewFrame(lastFrame.getNumber() + 1);
        }

        return lastFrame;
    }

    private Frame createNewFrame(int frameNumber) {
        return Frame.builder().number(frameNumber).game(this).build();
    }

    public boolean hasFinished() {
        return frames.size() == MAX_FRAMES && getLast(frames).hasFinished();
    }
}
