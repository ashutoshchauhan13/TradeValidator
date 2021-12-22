package com.gft.tradevalidator.validators;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateBoundaryConstraintValidator implements ConstraintValidator<DateBoundaryConstraint, Object> {

    private String field;
    private String fieldMatch;

    @Override
    public void initialize(DateBoundaryConstraint constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        LocalDate fieldValue = (LocalDate) new BeanWrapperImpl(value)
                .getPropertyValue(field);

        LocalDate fieldMatchValue = (LocalDate) new BeanWrapperImpl(value)
                .getPropertyValue(fieldMatch);

        if (fieldValue != null && fieldMatchValue != null) {
            return !fieldValue.isBefore(fieldMatchValue);
        } else {
            return false;
        }
    }
}
