package me.bill.startup;

import me.bill.entity.Currency;
import me.bill.repository.CurrencyRepository;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WikipediaCurrencyCrawlerTest {

    @Mock
    private Connection wikipediaConnection;
    @Mock
    private CurrencyRepository currencyRepository;

    @InjectMocks
    private WikipediaCurrencyCrawler wikipediaCurrencyCrawler = new WikipediaCurrencyCrawler();

    @Test
    public void testRun_ValidWikipediaResponse() throws IOException {
        when(wikipediaConnection.get())
                .thenReturn(Jsoup.parse(new File("src/test/resources/html/valid_currency_page.html"), "UTF-8"));
        ArgumentCaptor<List> currencyListCaptor = ArgumentCaptor.forClass(List.class);

        wikipediaCurrencyCrawler.run(null);

        verify(currencyRepository).saveAll(currencyListCaptor.capture());
        List<Currency> savedCurrencies = currencyListCaptor.getValue();
        assertThat(savedCurrencies, hasSize(178));
    }

    @Test
    public void testRun_IOExceptionFromWikipedia() throws IOException {
        when(wikipediaConnection.get()).thenThrow(new IOException());

        wikipediaCurrencyCrawler.run(null);

        verify(currencyRepository, never()).saveAll(any());
    }

}
