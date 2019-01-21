package bowling.controllers;

import bowling.models.Row;
import bowling.models.RowRequest;
import bowling.repositories.FrameRepository;
import bowling.repositories.RowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BowlingController {
    private RowRepository rowRepository;

    private FrameRepository frameRepository;

    @Autowired
    public BowlingController(RowRepository rowRepository, FrameRepository frameRepository) {
        this.rowRepository = rowRepository;
        this.frameRepository = frameRepository;
    }

    @PostMapping(value = "/rows")
    public Row save(@RequestBody final RowRequest rowRequest) throws Exception {
        return frameRepository
                .findById(rowRequest.getGameId())
                .map(frame -> rowRepository.save(Row.builder().pinsHit(10).frame(frame).build()))
                .orElseThrow(Exception::new);
    }
}
