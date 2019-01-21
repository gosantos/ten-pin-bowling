package bowling.repositories;

import bowling.models.Game;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GameRepositoryTest {

    @Autowired
    private GameRepository gameRepository;

    @Test
    public void shouldSaveAndFetchAGame() {
        final Game game = Game.builder().build();
        final Game savedGame = gameRepository.save(game);

        final Optional<Game> repositoryById = gameRepository.findById(savedGame.getId());

        assertThat(repositoryById, is(Optional.of(savedGame)));
    }

    @After
    public void tearDown() {
        gameRepository.deleteAll();
    }
}