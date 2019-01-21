package bowling;

import bowling.repositories.FrameRepository;
import bowling.repositories.GameRepository;
import bowling.repositories.RollRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BowlingE2ETest {

    @Autowired
    private RollRepository rollRepository;

    @Autowired
    private FrameRepository frameRepository;

    @Autowired
    private GameRepository gameRepository;

    @LocalServerPort
    private int port;

    @Test
    public void rowEndpoint() {
        final String jsonRequest = "{ \"gameId\": \"100\", \"pinsHit\": \"10\" }";

        given()
            .header("Content-Type", "application/json")
            .body(jsonRequest)
        .when().log().all()
            .post(getUrl("rolls"))
        .then().log().all()
            .statusCode(is(200))
            .body("id", equalTo(1), "pinsHit", equalTo(10));
    }

    @Test
    public void shouldReturn2xxWhenNewGameEndpointIsHit() {
        given()
                .header("Content-Type", "application/json")
        .when().log().all()
                .post(getUrl("new-game"))
        .then().log().all()
                .statusCode(is(200));
    }

    @After
    public void tearDown() {
        rollRepository.deleteAll();
        frameRepository.deleteAll();
        gameRepository.deleteAll();
    }

    private String getUrl(String endpoint) {
        return String.format("http://localhost:%s/%s", port, endpoint);
    }
}
