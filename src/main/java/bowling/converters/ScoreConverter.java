package bowling.converters;

import bowling.models.Frame;
import bowling.models.Game;
import bowling.models.Roll;
import bowling.models.Score;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScoreConverter implements Converter<Game, Score> {
    @Override
    public Score convert(Game game) {
        final List<Integer> rolls = game.getFrames().stream()
                .map(Frame::getRolls)
                .flatMap(Collection::stream)
                .map(Roll::getPinsHit)
                .collect(Collectors.toList());

        final Integer numberOfFrames = game.getFrames().size();

        return Score.builder().numberOfFrames(numberOfFrames).rolls(rolls).build();
    }
}
