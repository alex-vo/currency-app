package me.bill.validator;

import me.bill.exception.CurrencyCodeValidationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CurrencyCodeValidator {

    public static final Integer CURRENCY_CODE_LENGTH = 3;

    public void validate(String currencyCode) throws CurrencyCodeValidationException {
        if (StringUtils.length(currencyCode) != CURRENCY_CODE_LENGTH) {
            throw new CurrencyCodeValidationException(String.format("Currency length must is not equal to %d",
                    CURRENCY_CODE_LENGTH));
        }
    }

}
