package bowling.services;

import bowling.exceptions.GameFinishedException;
import bowling.exceptions.GameNotFoundException;
import bowling.models.Frame;
import bowling.models.Game;
import bowling.models.Roll;
import bowling.repositories.GameRepository;
import bowling.repositories.RollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BowlingService {

    private GameRepository gameRepository;
    private RollRepository rollRepository;

    @Autowired
    public BowlingService(GameRepository gameRepository, RollRepository rollRepository) {
        this.gameRepository = gameRepository;
        this.rollRepository = rollRepository;
    }

    public Game findGameById(long gameId) {
        return gameRepository.findById(gameId).orElseThrow(GameNotFoundException::new);
    }

    public Roll updateGame(Game game, Integer pinsHit) {
        if (game.hasFinished()) {
            throw new GameFinishedException();
        }

        final Frame currentFrame = game.getCurrentFrame();

        final Roll roll = Roll.builder().frame(currentFrame).pinsHit(pinsHit).build();

        return rollRepository.save(roll);
    }

    public Game newGame() {
        return gameRepository.save(Game.builder().build());
    }
}
