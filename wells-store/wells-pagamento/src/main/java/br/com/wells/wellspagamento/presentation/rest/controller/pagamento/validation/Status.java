package br.com.wells.wellspagamento.presentation.rest.controller.pagamento.validation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.FIELD;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = StatusValidatorImpl.class)
public @interface Status {
    String message() default "O status do pagamento não existente.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

