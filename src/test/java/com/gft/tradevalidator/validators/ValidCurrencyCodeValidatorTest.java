package com.gft.tradevalidator.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@ExtendWith(MockitoExtension.class)
class ValidCurrencyCodeValidatorTest {

    @Mock
    private ConstraintValidatorContext constraintValidatorContext;

    @Test
    public void test_validation_with_incorrect_currency_pair()
            throws Exception {
        ValidCurrencyCodeValidator  validCurrencyCodeValidator = new ValidCurrencyCodeValidator();
        boolean result = validCurrencyCodeValidator.isValid("EDRUSD",constraintValidatorContext);
        assertFalse("currencyPair check passed when incorrect pair is passed",result);
    }

    @Test
    public void test_validation_with_correct_currency_pair()
            throws Exception {
        ValidCurrencyCodeValidator  validCurrencyCodeValidator = new ValidCurrencyCodeValidator();
        boolean result = validCurrencyCodeValidator.isValid("EURUSD",constraintValidatorContext);
        assertTrue("currencyPair check passed when incorrect pair is passed",result);
    }

    @Test
    public void test_validation_with_incorrect_currency_pair_length()
            throws Exception {
        ValidCurrencyCodeValidator  validCurrencyCodeValidator = new ValidCurrencyCodeValidator();
        boolean result = validCurrencyCodeValidator.isValid("EURUS",constraintValidatorContext);
        assertFalse("currencyPair check passed when incorrect pair length is passed",result);
    }

    @Test
    public void test_validation_with_incorrect_currency_pair_with_empty_value()
            throws Exception {
        ValidCurrencyCodeValidator  validCurrencyCodeValidator = new ValidCurrencyCodeValidator();
        boolean result = validCurrencyCodeValidator.isValid("",constraintValidatorContext);
        assertFalse("currencyPair check passed when incorrect pair length is passed",result);
    }

}