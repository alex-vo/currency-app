package me.bill.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

public class CurrencyCodeValidator {

    public static final Integer EXPECTED_CURRENCY_CODE_LENGTH = 3;

    public static void validate(String currencyCode) {
        List<String> errors = new ArrayList<>();
        if (StringUtils.length(currencyCode) != EXPECTED_CURRENCY_CODE_LENGTH) {
            errors.add(String.format("Currency length must be equal to %d", EXPECTED_CURRENCY_CODE_LENGTH));
        }

        if (!CollectionUtils.isEmpty(errors)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.join(", ", errors));
        }
    }

}
