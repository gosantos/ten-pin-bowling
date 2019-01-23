package bowling;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BowlingE2ETest {
    @LocalServerPort
    private int port;

    @Test
    public void shouldReturn2xxWhenNewGameEndpointIsHit() {
        given()
                .header("Content-Type", "application/json")
                .when().log().all()
                .post(getUrl("new-game"))
                .then().log().all()
                .statusCode(is(200));
    }

    @Test
    public void shouldReturn2xxWhenScoreEndpointIsHit() {
        given()
                .header("Content-Type", "application/json")
                .when().log().all()
                .get(getUrl("score/{gameId}"), 1)
                .then().log().all()
                .statusCode(is(200));
    }

    @Test
    public void shouldReturn2xxWhenRollEndpointIsHit() {
        final String jsonRequest = "{ \"gameId\": \"1\", \"pinsHit\": \"10\" }";

        given()
                .header("Content-Type", "application/json")
                .body(jsonRequest)
                .when().log().all()
                .post(getUrl("rolls"))
                .then().log().all()
                .statusCode(is(200))
                .body("pinsHit", equalTo(10));
    }

    private String getUrl(String endpoint) {
        return String.format("http://localhost:%s/%s", port, endpoint);
    }
}
