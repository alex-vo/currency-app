package me.bill.validation;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class CurrencyCodeValidatorTest {

    @Test
    public void testValidate_WrongLength() {
        validateAndAssertResponseStatusException("A");
        validateAndAssertResponseStatusException("ABCDEF");
    }

    private void validateAndAssertResponseStatusException(String currencyCode) {
        try {
            CurrencyCodeValidator.validate(currencyCode);
            fail();
        } catch (ResponseStatusException e) {
            assertThat(e.getStatus(), is(HttpStatus.BAD_REQUEST));
            assertThat(e.getReason(), is(String.format("Currency length must be equal to %d", CurrencyCodeValidator.EXPECTED_CURRENCY_CODE_LENGTH)));
        }
    }

    @Test
    public void testValidate_Success() {
        CurrencyCodeValidator.validate("EUR");
        CurrencyCodeValidator.validate("ABC");
    }

}
