package me.bill.e2e;

import com.jayway.restassured.RestAssured;
import me.bill.repository.CurrencyRepository;
import me.bill.repository.RequestRepository;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = LogTest.ValidWikipediaResponseConfiguration.class
)
public class LogTest {

    @TestConfiguration
    static class ValidWikipediaResponseConfiguration {

        @Bean
        public Connection wikipediaConnection() throws IOException {
            Connection connection = mock(Connection.class);
            when(connection.get()).thenReturn(Jsoup.parse(new File("src/test/resources/html/valid_currency_page.html"), "UTF-8"));
            return connection;
        }

    }

    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private CurrencyRepository currencyRepository;

    @LocalServerPort
    public void setPort(int port) {
        RestAssured.port = port;
    }

    @Before
    public void setUp() {
        requestRepository.deleteAll();
    }

    @Test
    public void testRequestsAreLogged() {
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println(currencyRepository.count());
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAA");
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
