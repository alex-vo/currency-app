package me.bill.e2e;

import com.jayway.restassured.RestAssured;
import me.bill.e2e.configuration.ValidWikipediaResponseConfiguration;
import me.bill.repository.RequestRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ValidWikipediaResponseConfiguration.class
)
public class LogTest {

    @Autowired
    private RequestRepository requestRepository;

    @LocalServerPort
    public void setPort(int port) {
        RestAssured.port = port;
    }

    @Before
    public void setUp() {
        requestRepository.deleteAll();
    }

    @Test
    public void testRequestsAreLogged() throws IOException {
        given().when().get("/currency/EUR").then().statusCode(200);
        given().when().get("/currency/USD").then().statusCode(200);
        given().when().get("/currency/JPY").then().statusCode(200);
        given().when().get("/currency/RUB").then().statusCode(200);
        given().when().get("/currency/AAA").then().statusCode(404);
        given().when().get("/currency/invalid_currecny").then().statusCode(400);
        given().when().get("/abc/abc").then().statusCode(404);
        given().when().get("/log").then().statusCode(200);

        assertThat(requestRepository.count(), is(6l));
    }

}
