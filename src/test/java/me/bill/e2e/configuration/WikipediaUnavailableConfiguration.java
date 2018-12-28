package me.bill.e2e.configuration;

import org.jsoup.Connection;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestConfiguration
public class WikipediaUnavailableConfiguration {

    @Bean
    public Connection wikipediaConnection() throws IOException {
        Connection connection = mock(Connection.class);
        when(connection.get()).thenThrow(new IOException());
        return connection;
    }

}
