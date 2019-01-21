package bowling;

import bowling.repositories.FrameRepository;
import bowling.repositories.GameRepository;
import bowling.repositories.RowRepository;
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
    private RowRepository rowRepository;

    @Autowired
    private FrameRepository frameRepository;

    @Autowired
    private GameRepository gameRepository;

    @LocalServerPort
    private int port;

    @Test
    public void rowEndpoint() {
        final String jsonRequest = "{ \"gameId\": \"3296288679181027561\", \"pinsHit\": \"10\" }";

        given()
            .header("Content-Type", "application/json")
            .body(jsonRequest)
        .when()
            .post(getUrl("rows"))
        .then().log().all()
            .statusCode(is(200))
            .body("id", equalTo(1), "pinsHit", equalTo(10));
    }

    @Test
    public void shouldReturn2xxWhenNewGameEndpointIsHit() {
        given()
                .header("Content-Type", "application/json")
        .when()
                .post(getUrl("new-game"))
        .then()
                .statusCode(is(200));
    }

    @After
    public void tearDown() {
        rowRepository.deleteAll();
        frameRepository.deleteAll();
        gameRepository.deleteAll();
    }

    private String getUrl(String endpoint) {
        return String.format("http://localhost:%s/%s", port, endpoint);
    }
}
