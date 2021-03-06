package bowling.controllers;

import bowling.converters.ScoreConverter;
import bowling.models.Game;
import bowling.models.Score;
import bowling.models.ScoreResponse;
import bowling.services.BowlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ScoreController {
    private BowlingService bowlingService;
    private ScoreConverter scoreConverter;

    @Autowired
    public ScoreController(BowlingService bowlingService, ScoreConverter scoreConverter) {
        this.bowlingService = bowlingService;
        this.scoreConverter = scoreConverter;
    }

    @GetMapping(value = "/scores/{gameId}")
    public ScoreResponse getScore(@PathVariable Long gameId) {
        final Game game = bowlingService.findGameById(gameId);

        final Score score = scoreConverter.convert(game);

        final Map<Integer, Integer> scoreByFrame = score.calculateScoreByFrame();

        final Integer totalScore = score.calculateTotalScore();

        return ScoreResponse.builder().gameId(gameId).scoreByFrame(scoreByFrame).totalScore(totalScore).build();
    }
}
