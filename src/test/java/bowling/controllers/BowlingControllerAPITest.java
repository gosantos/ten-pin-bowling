package bowling.controllers;

import bowling.models.Frame;
import bowling.models.Game;
import bowling.models.Roll;
import bowling.repositories.GameRepository;
import bowling.repositories.RollRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = BowlingController.class)
public class BowlingControllerAPITest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RollRepository rollRepository;

    @MockBean
    private GameRepository gameRepository;

    @Test
    public void shouldReturn2xxWhenPostingARow() throws Exception {
        final Frame frame = Frame.builder().build();
        final Game game = Game.builder().frames(Arrays.asList(frame)).build();
        given(gameRepository.findById(100L)).willReturn(Optional.of(game));

        final Roll roll = Roll.builder().pinsHit(10).frame(frame).build();
        given(rollRepository.save(roll)).willReturn(roll);

        final String jsonRequest = "{ \"gameId\": \"100\", \"pinsHit\": \"10\" }";

        mockMvc.perform(post("/rolls")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonRequest))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldReturn2xxWhenNewGameEndpointIsHit() throws Exception {
        final Game game = Game.builder().build();
        given(gameRepository.save(game)).willReturn(game);

        mockMvc.perform(post("/new-game")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is2xxSuccessful());
    }
}