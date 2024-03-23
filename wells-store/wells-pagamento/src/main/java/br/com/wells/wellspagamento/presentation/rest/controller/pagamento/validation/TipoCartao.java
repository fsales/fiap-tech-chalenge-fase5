package br.com.wells.wellspagamento.presentation.rest.controller.pagamento.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;

@Target({FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = TipoCartaoValidatorImpl.class)
public @interface TipoCartao {
    String message() default "O tipo do cartão não existente.";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
