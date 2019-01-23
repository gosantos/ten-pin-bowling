package bowling.controllers;

import bowling.models.Frame;
import bowling.models.Game;
import bowling.models.Score;
import bowling.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScoreController {
    private GameRepository gameRepository;

    @Autowired
    public ScoreController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping(value = "/score/{gameId}")
    public Score getScore(@PathVariable Long gameId) {
        final Game game = gameRepository.findById(gameId).get();

        final Score build = Score.builder().gameId(gameId).frames((List<Frame>) game.getFrames()).build();

        return build;
    }

}
