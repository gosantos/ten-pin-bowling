package bowling.controllers;

import bowling.converters.ScoreConverter;
import bowling.exceptions.GameNotFoundException;
import bowling.models.Game;
import bowling.models.Score;
import bowling.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScoreController {
    private GameRepository gameRepository;
    private ScoreConverter scoreConverter;

    @Autowired
    public ScoreController(GameRepository gameRepository, ScoreConverter scoreConverter) {
        this.gameRepository = gameRepository;
        this.scoreConverter = scoreConverter;
    }

    @GetMapping(value = "/scores/{gameId}")
    public Score getScore(@PathVariable Long gameId) {
        final Game game = gameRepository.findById(gameId).orElseThrow(GameNotFoundException::new);

        final Score score = scoreConverter.convert(game);

        score.calculateScore();

        return score;
    }
}
