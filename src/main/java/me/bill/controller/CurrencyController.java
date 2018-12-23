package me.bill.controller;

import me.bill.dto.CurrencyDTO;
import me.bill.entity.Currency;
import me.bill.repository.CurrencyRepository;
import me.bill.validation.CurrencyCodeValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class CurrencyController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CurrencyRepository currencyRepository;

    @GetMapping(value = "currency/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CurrencyDTO getCurrencyByCode(@PathVariable("code") String code) {
        CurrencyCodeValidator.validate(code);
        Currency currency = currencyRepository.getByCodeIgnoreCase(code);
        if (currency == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Currency with code %s not found", code));
        }
        return modelMapper.map(currency, CurrencyDTO.class);
    }

}
