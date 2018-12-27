package me.bill.e2e;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import me.bill.dto.CurrencyDTO;
import me.bill.dto.ErrorResponseDTO;
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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CurrencyIntegrationTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @LocalServerPort
    public void setPort(int port) {
        RestAssured.port = port;
    }

    @Test
    public void testGetEUR_Success() throws IOException {
        CurrencyDTO actualResult = given().basePath("/currency").when()
                .get("EUR")
                .then()
                .statusCode(200)
                .extract()
                .as(CurrencyDTO.class);
        CurrencyDTO expectedValue = objectMapper.readValue(
                new File("src/test/resources/json/euro_success.json"),
                CurrencyDTO.class
        );
        assertThat(actualResult, is(expectedValue));
    }

    @Test
    public void testGetABC_NotFound() throws IOException {
        ErrorResponseDTO actualResult = given().basePath("/currency").when()
                .get("ABC")
                .then()
                .statusCode(404)
                .extract()
                .as(ErrorResponseDTO.class);
        ErrorResponseDTO expectedValue = objectMapper.readValue(
                new File("src/test/resources/json/abc_not_found.json"),
                ErrorResponseDTO.class
        );
        assertThat(actualResult, is(expectedValue));
    }

    @Test
    public void testGetZZZZ_ValidationFailed_BadRequest() throws IOException {
        ErrorResponseDTO actualResult = given().basePath("/currency").when()
                .get("ZZZZ")
                .then()
                .statusCode(400)
                .extract()
                .as(ErrorResponseDTO.class);
        ErrorResponseDTO expectedValue = objectMapper.readValue(
                new File("src/test/resources/json/zzzz_bad_request.json"),
                ErrorResponseDTO.class
        );
        assertThat(actualResult, is(expectedValue));
    }

}
