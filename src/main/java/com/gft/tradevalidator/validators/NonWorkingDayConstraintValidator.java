package com.gft.tradevalidator.validators;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class NonWorkingDayConstraintValidator implements ConstraintValidator<NonWorkingDayConstraint, LocalDate> {

    @Override
    public void initialize(NonWorkingDayConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext context) {
        return !isWeekend(localDate);
    }

    public boolean isWeekend(final LocalDate localDate)
    {
        DayOfWeek day = DayOfWeek.of(localDate.get(ChronoField.DAY_OF_WEEK));
        return day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY;
    }
}
