package me.bill.e2e;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import me.bill.dto.CurrencyDTO;
import me.bill.dto.ErrorResponseDTO;
import me.bill.e2e.configuration.ValidWikipediaResponseConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ValidWikipediaResponseConfiguration.class
)
public class CurrencyAppIntegrationTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @LocalServerPort
    public void setPort(int port) {
        RestAssured.port = port;
    }

    @Test
    public void getEUR_Success() throws IOException {
        CurrencyDTO actualResponse = given().when()
                .get("/currency/EUR")
                .then()
                .statusCode(200)
                .extract()
                .as(CurrencyDTO.class);
        CurrencyDTO expectedResponse = objectMapper.readValue(
                new File("src/test/resources/json/euro_success.json"),
                CurrencyDTO.class
        );
        assertThat(actualResponse, is(expectedResponse));
    }

    @Test
    public void getABC_NotFound() throws IOException {
        ErrorResponseDTO actualResponse = given().when()
                .get("/currency/ABC")
                .then()
                .statusCode(404)
                .extract()
                .as(ErrorResponseDTO.class);
        ErrorResponseDTO expectedResponse = objectMapper.readValue(
                new File("src/test/resources/json/abc_not_found.json"),
                ErrorResponseDTO.class
        );
        assertThat(actualResponse, is(expectedResponse));
    }

    @Test
    public void getInvalidCurrency_ValidationFailed_BadRequest() throws IOException {
        ErrorResponseDTO actualResponse = given().when()
                .get("/currency/sinvalid_currency")
                .then()
                .statusCode(400)
                .extract()
                .as(ErrorResponseDTO.class);
        ErrorResponseDTO expectedResponse = objectMapper.readValue(
                new File("src/test/resources/json/invalid_currency_bad_request.json"),
                ErrorResponseDTO.class
        );
        assertThat(actualResponse, is(expectedResponse));
    }

}
