package br.com.wells.wellspagamento.presentation.rest.controller.pagamento.validation;

import br.com.wells.core.domain.pagamento.model.enumeration.StatusPagamento;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StatusValidatorImpl implements ConstraintValidator<Status, String> {

	@Override
	public void initialize(Status constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
		return value ==null || StatusPagamento.contains(value);
	}

}
