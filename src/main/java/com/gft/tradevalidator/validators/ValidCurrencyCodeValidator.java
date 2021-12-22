package com.gft.tradevalidator.validators;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Currency;
import java.util.Set;

public class ValidCurrencyCodeValidator implements ConstraintValidator<ValidCurrencyCode, String> {


    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(!StringUtils.hasText(value)){
            return false;
        }
        if(value.length() != 6)
        {
            return false;
        }
        String first = value.substring(0,3);
        String second = value.substring(3,6);
        return isValidCurrency(first) && isValidCurrency(second);
    }

    private boolean isValidCurrency(String currencyCode)
    {
        Set<Currency> currencies = Currency.getAvailableCurrencies();
        try {
            currencies.contains(Currency.getInstance(currencyCode));
            return true;
        }catch(IllegalArgumentException e){
            return false;
        }
    }
}
