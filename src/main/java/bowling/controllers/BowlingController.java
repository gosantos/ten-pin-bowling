package bowling.controllers;

import bowling.models.Game;
import bowling.models.Row;
import bowling.models.RowRequest;
import bowling.repositories.FrameRepository;
import bowling.repositories.GameRepository;
import bowling.repositories.RowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BowlingController {
    private RowRepository rowRepository;
    private FrameRepository frameRepository;
    private GameRepository gameRepository;

    @Autowired
    public BowlingController(RowRepository rowRepository, FrameRepository frameRepository, GameRepository gameRepository) {
        this.rowRepository = rowRepository;
        this.frameRepository = frameRepository;
        this.gameRepository = gameRepository;
    }

    @PostMapping(value = "/rows")
    public Row save(@RequestBody final RowRequest rowRequest) throws Exception {
        return frameRepository
                .findById(rowRequest.getGameId())
                .map(frame -> rowRepository.save(Row.builder().pinsHit(10).frame(frame).build()))
                .orElseThrow(Exception::new);
    }

    @PostMapping(value = "/new-game")
    public Game newGame() {
        return gameRepository.save(Game.builder().build());
    }
}
