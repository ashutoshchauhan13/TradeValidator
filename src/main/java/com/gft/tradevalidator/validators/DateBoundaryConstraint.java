package com.gft.tradevalidator.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

@Constraint(validatedBy = DateBoundaryConstraintValidator.class)
@Target({ TYPE, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DateBoundaryConstraint {
    String field();

    String fieldMatch();

    String message() default "Date1 is not before Date2";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        DateBoundaryConstraint[] value();
    }
}
