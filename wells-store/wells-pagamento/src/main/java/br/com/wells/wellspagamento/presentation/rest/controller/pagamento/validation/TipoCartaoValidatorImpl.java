package br.com.wells.wellspagamento.presentation.rest.controller.pagamento.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TipoCartaoValidatorImpl implements ConstraintValidator<TipoCartao, String> {

	@Override
	public void initialize(TipoCartao constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
		return value ==null || br.com.wells.core.domain.pagamento.model.enumeration.TipoCartao.contains(value);
	}

}
