package me.bill.startup;

import lombok.extern.slf4j.Slf4j;
import me.bill.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WikipediaCurrencyCrawler implements ApplicationRunner {

    @Autowired
    CurrencyRepository currencyRepository;

    @Override
    public void run(ApplicationArguments args) {
        log.info("I am going to get a lot of data from Wikipedia");
    }
}
