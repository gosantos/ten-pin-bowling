package bowling.controllers;

import bowling.models.Frame;
import bowling.models.Row;
import bowling.models.RowRequest;
import bowling.repositories.FrameRepository;
import bowling.repositories.RowRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = RowController.class)
public class RowControllerAPITest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RowRepository rowRepository;

    @MockBean
    private FrameRepository frameRepository;

    @Test
    public void shouldReturn2xxWhenPostingARow() throws Exception {
        final Frame frame = Frame.builder().id(1L).build();
        final Optional<Frame> optionalFrame = Optional.of(frame);
        given(frameRepository.findById(1L)).willReturn(optionalFrame);

        final Row row = Row.builder().pinsHit(10).frame(frame).build();
        given(rowRepository.save(row)).willReturn(row);

        final String jsonRequest = "{ \"rowId\": \"1\", \"pinsHit\": \"10\" }";

        mockMvc.perform(post("/rows")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonRequest))
                .andExpect(status().is2xxSuccessful());
    }
}