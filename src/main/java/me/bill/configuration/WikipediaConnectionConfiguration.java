package me.bill.configuration;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WikipediaConnectionConfiguration {

    @Value("${wikipedia.article.url}")
    private String wikipediaArticleUrl;

    @Bean
    public Connection wikipediaConnection() {
        return Jsoup.connect(wikipediaArticleUrl);
    }

}
