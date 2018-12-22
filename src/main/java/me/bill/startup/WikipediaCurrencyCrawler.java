package me.bill.startup;

import lombok.extern.slf4j.Slf4j;
import me.bill.entity.Currency;
import me.bill.repository.CurrencyRepository;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class WikipediaCurrencyCrawler implements ApplicationRunner {

    public static final String ACTIVE_CODES_ELEMENT_ID = "Active_codes";
    public static final String CURRENCY_TABLE_SELECTOR = ".wikitable.sortable";

    @Value("${wikipedia.article.url}")
    private String wikipediaArticleUrl;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public void run(ApplicationArguments args) {
        try {
            log.info("Started getting currency data from Wikipedia");
            List<Currency> currencies = getCurrenciesFromWikipedia();
            currencyRepository.saveAll(currencies);
            log.info("Successfully finished getting currency data from Wikipedia");
        } catch (Exception e) {
            log.error("Failed to read currency data from Wikipedia", e);
        }
    }

    private List<Currency> getCurrenciesFromWikipedia() throws IOException {
        Document doc = Jsoup.connect(wikipediaArticleUrl).get();
        Element currencyTable = doc.getElementById(ACTIVE_CODES_ELEMENT_ID)
                .parent()
                .siblingElements()
                .select(CURRENCY_TABLE_SELECTOR)
                .first();

        return currencyTable.select("tr")
                .stream()
                .map(tr -> {
                    tr.select("sup").remove();
                    return tr.select("td");
                })
                .filter(tds -> !tds.isEmpty())
                .map(tds -> {
                    Currency currency = new Currency();
                    currency.setCode(tds.get(0).text());
                    currency.setNum(Integer.valueOf(tds.get(1).text()));
                    currency.setE(StringUtils.left(tds.get(2).text(), 1));
                    currency.setCurrency(tds.get(3).text());
                    return currency;
                })
                .collect(Collectors.toList());
    }
}
