package bowling.controllers;

import bowling.models.Row;
import bowling.models.RowRequest;
import bowling.repositories.RowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RowController {
    private RowRepository rowRepository;

    @Autowired
    public RowController(RowRepository rowRepository) {
        this.rowRepository = rowRepository;
    }

    @PostMapping(value = "/rows")
    public ResponseEntity<Row> save(@RequestBody final RowRequest rowRequest) {
        final Row row = new Row(rowRequest.getRowId(), rowRequest.getPinsHit());
        final Row savedRow = rowRepository.save(row);

        return ResponseEntity.ok(savedRow);
    }
}
