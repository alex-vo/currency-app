package me.bill.e2e.configuration;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestConfiguration
public class ValidWikipediaResponseConfiguration {

    @Bean
    public Connection wikipediaConnection() throws IOException {
        Connection connection = mock(Connection.class);
        when(connection.get()).thenReturn(Jsoup.parse(new File("src/test/resources/html/valid_currency_page.html"), "UTF-8"));
        return connection;
    }

}
