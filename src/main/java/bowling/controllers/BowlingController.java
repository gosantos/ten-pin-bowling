package bowling.controllers;

import bowling.models.Game;
import bowling.models.Roll;
import bowling.models.RollRequest;
import bowling.services.BowlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

    @PostMapping(value = "/rolls")
    public Roll save(@RequestBody final RollRequest rollRequest) {
        final Game game = bowlingService.findGameById(rollRequest.getGameId());

        return bowlingService.updateGame(game, rollRequest.getPinsHit());
    }

    @PostMapping(value = "/games")
    public Game newGame() {
        return bowlingService.newGame();
    }
}
