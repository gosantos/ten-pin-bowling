package bowling.controllers;

import bowling.models.Game;
import bowling.models.Roll;
import bowling.models.RollRequest;
import bowling.services.BowlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BowlingController {
    private BowlingService bowlingService;

    @Autowired
    public BowlingController(BowlingService bowlingService) {
        this.bowlingService = bowlingService;
    }

    @PostMapping(value = "/games/{gameId}/rolls")
    public Roll updateGame(@PathVariable("gameId") Long gameId, @RequestBody final RollRequest rollRequest) {
        final Game game = bowlingService.findGameById(gameId);

        return bowlingService.updateGame(game, rollRequest.getPinsHit());
    }

    @PostMapping(value = "/games")
    public Game newGame() {
        return bowlingService.newGame();
    }
}
