package bowling.controllers;

import bowling.exceptions.GameFinishedException;
import bowling.models.Frame;
import bowling.models.Game;
import bowling.models.Roll;
import bowling.models.RollRequest;
import bowling.repositories.GameRepository;
import bowling.repositories.RollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BowlingController {
    private RollRepository rollRepository;
    private GameRepository gameRepository;

    @Autowired
    public BowlingController(RollRepository rollRepository, GameRepository gameRepository) {
        this.rollRepository = rollRepository;
        this.gameRepository = gameRepository;
    }

    @PostMapping(value = "/rolls")
    public Roll save(@RequestBody final RollRequest rollRequest) throws GameFinishedException {
        final Frame frame = gameRepository.findById(rollRequest.getGameId()).map(Game::getLatestFrame).orElseThrow(GameFinishedException::new);

        final Roll roll = Roll.builder().frame(frame).pinsHit(rollRequest.getPinsHit()).build();
        return rollRepository.save(roll);
    }

    @PostMapping(value = "/new-game")
    public Game newGame() {
        return gameRepository.save(Game.builder().build());
    }
}
