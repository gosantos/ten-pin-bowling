package bowling;

import bowling.controllers.BowlingController;
import bowling.controllers.ScoreController;
import bowling.exceptions.GameFinishedException;
import bowling.exceptions.GameNotFoundException;
import bowling.models.Frame;
import bowling.models.Game;
import bowling.models.Roll;
import bowling.services.BowlingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {BowlingController.class, ScoreController.class})
public class APITests {
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

        final String jsonRequest = "{ \"pinsHit\": \"10\" }";

        mockMvc.perform(post("/games/100/rolls")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonRequest))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldReturn4xxWhenThereIsNoGameWithTheGivenId() throws Exception {
        final String jsonRequest = "{ \"gameId\": \"584\", \"pinsHit\": \"10\" }";

        given(bowlingService.findGameById(584L)).willThrow(GameNotFoundException.class);

        mockMvc.perform(post("/games/584/rolls")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonRequest))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldReturn4xxWhenGameHasFinished() throws Exception {
        final String jsonRequest = "{ \"pinsHit\": \"10\" }";

        final Game game = Game.builder().build();

        given(bowlingService.findGameById(13L)).willReturn(game);
        given(bowlingService.updateGame(game, 10)).willThrow(GameFinishedException.class);

        mockMvc.perform(post("/games/13/rolls")
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

    @Test
    public void shouldReturn4xxWhenThereIsNoScoreWithTheGivenId() throws Exception {
        given(bowlingService.findGameById(100L)).willThrow(GameNotFoundException.class);

        mockMvc.perform(get("/scores/100")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldReturn2xxWhenScoreEndpointIsHit() throws Exception {
        final Frame frame = Frame.builder().build();
        final Game game = Game.builder().frames(Arrays.asList(frame)).build();
        given(bowlingService.findGameById(100L)).willReturn(game);

        mockMvc.perform(get("/scores/100")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is2xxSuccessful());
    }
}