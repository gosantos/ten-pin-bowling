package bowling.controllers;

import bowling.exceptions.GameFinishedException;
import bowling.exceptions.GameNotFoundException;
import bowling.models.Frame;
import bowling.models.Game;
import bowling.models.Roll;
import bowling.repositories.GameRepository;
import bowling.repositories.RollRepository;
import bowling.services.BowlingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = BowlingController.class)
public class BowlingControllerAPITest {
    private static final int FIRST_FRAME = 1;
    private static final int LAST_FRAME = 10;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BowlingService bowlingService;

    @Test
    public void shouldReturn2xxWhenUpdatingTheGame() throws Exception {
        final Frame frame = Frame.builder().build();
        final Game game = Game.builder().frames(Arrays.asList(frame)).build();
        given(bowlingService.findGameById(100L)).willReturn(game);

        final Roll roll = Roll.builder().pinsHit(10).frame(frame).build();
        given(bowlingService.updateGame(game, 10)).willReturn(roll);

        final String jsonRequest = "{ \"gameId\": \"100\", \"pinsHit\": \"10\" }";

        mockMvc.perform(post("/rolls")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonRequest))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldReturn4xxWhenThereIsNoGameWithTheGivenId() throws Exception {
        final String jsonRequest = "{ \"gameId\": \"584\", \"pinsHit\": \"10\" }";

        given(bowlingService.findGameById(584L)).willThrow(GameNotFoundException.class);

        mockMvc.perform(post("/rolls")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonRequest))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldReturn4xxWhenGameHasFinished() throws Exception {
        final String jsonRequest = "{ \"gameId\": \"13\", \"pinsHit\": \"10\" }";

        final Game game = Game.builder().build();

        given(bowlingService.findGameById(13L)).willReturn(game);
        given(bowlingService.updateGame(game, 10)).willThrow(GameFinishedException.class);

        mockMvc.perform(post("/rolls")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonRequest))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldReturn2xxWhenNewGameEndpointIsHit() throws Exception {
        final Game game = Game.builder().build();
        given(bowlingService.newGame()).willReturn(game);

        mockMvc.perform(post("/games")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is2xxSuccessful());
    }
}