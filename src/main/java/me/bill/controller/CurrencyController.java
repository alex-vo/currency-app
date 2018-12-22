package me.bill.controller;

import me.bill.dto.CurrencyDTO;
import me.bill.entity.Currency;
import me.bill.repository.CurrencyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CurrencyController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CurrencyRepository currencyRepository;

    @GetMapping(value = "currency/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CurrencyDTO getCurrencyByCode(@PathVariable("code") String code) {
        Currency currency = currencyRepository.getByCodeIgnoreCase(code);
        return modelMapper.map(currency, CurrencyDTO.class);
    }

}
