package com.gft.tradevalidator.validators;

import com.gft.tradevalidator.model.TradeRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@ExtendWith(MockitoExtension.class)
class DateBoundaryConstraintConstraintValidatorTest {

    private static final String DATE_FORMAT_INPUT = "yyyy-MM-dd";
    @Mock
    private DateBoundaryConstraint dateBoundaryConstraint;

    @Mock
    private ConstraintValidatorContext constraintValidatorContext;

    @Test
    public void test_validation_passes_if_value_date_is_before_trade_date()
            throws Exception {
        when(dateBoundaryConstraint.field()).thenReturn("valueDate");
        when(dateBoundaryConstraint.fieldMatch()).thenReturn("tradeDate");

        DateBoundaryConstraintValidator dateBoundaryConstraintValidator = new DateBoundaryConstraintValidator();
        dateBoundaryConstraintValidator.initialize(dateBoundaryConstraint);

        TradeRequest tradeRequest = new TradeRequest();
        tradeRequest.setValueDate(LocalDate.parse("2020-07-15", DateTimeFormatter.ofPattern(DATE_FORMAT_INPUT)));
        tradeRequest.setTradeDate(LocalDate.parse("2020-07-12", DateTimeFormatter.ofPattern(DATE_FORMAT_INPUT)));

        boolean result = dateBoundaryConstraintValidator.isValid(tradeRequest,constraintValidatorContext);
        assertTrue("valueDate check failed when smaller value of tradeDate passed",result);
    }

    @Test
    public void test_validation_fails_if_value_date_is_before_trade_date()
            throws Exception {
        when(dateBoundaryConstraint.field()).thenReturn("valueDate");
        when(dateBoundaryConstraint.fieldMatch()).thenReturn("tradeDate");

        DateBoundaryConstraintValidator dateBoundaryConstraintValidator = new DateBoundaryConstraintValidator();
        dateBoundaryConstraintValidator.initialize(dateBoundaryConstraint);

        TradeRequest tradeRequest = new TradeRequest();
        tradeRequest.setValueDate(LocalDate.parse("2020-06-15", DateTimeFormatter.ofPattern(DATE_FORMAT_INPUT)));
        tradeRequest.setTradeDate(LocalDate.parse("2020-07-12", DateTimeFormatter.ofPattern(DATE_FORMAT_INPUT)));

        boolean result = dateBoundaryConstraintValidator.isValid(tradeRequest,constraintValidatorContext);
        assertFalse("valueDate check failed when larger value of tradeDate passed",result);
    }

    @Test
    public void test_validation_fails_if_value_date_is_equal_to_trade_date()
            throws Exception {
        when(dateBoundaryConstraint.field()).thenReturn("valueDate");
        when(dateBoundaryConstraint.fieldMatch()).thenReturn("tradeDate");

        DateBoundaryConstraintValidator dateBoundaryConstraintValidator = new DateBoundaryConstraintValidator();
        dateBoundaryConstraintValidator.initialize(dateBoundaryConstraint);

        TradeRequest tradeRequest = new TradeRequest();
        tradeRequest.setValueDate(LocalDate.parse("2020-07-15", DateTimeFormatter.ofPattern(DATE_FORMAT_INPUT)));
        tradeRequest.setTradeDate(LocalDate.parse("2020-07-15", DateTimeFormatter.ofPattern(DATE_FORMAT_INPUT)));

        boolean result = dateBoundaryConstraintValidator.isValid(tradeRequest,constraintValidatorContext);
        assertTrue("valueDate check failed when equal value of tradeDate passed",result);
    }
}