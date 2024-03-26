package br.com.wells.wellspagamento.infrastructure.database.postgres.entity.enumeration.converter;

import br.com.wells.wellspagamento.infrastructure.database.postgres.entity.enumeration.StatusPagamentoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusPagamentoConverter implements AttributeConverter<StatusPagamentoEnum, String> {

	@Override
	public String convertToDatabaseColumn(StatusPagamentoEnum statusPagamento) {
		return statusPagamento == null ? null : statusPagamento.name();
	}

	@Override
	public StatusPagamentoEnum convertToEntityAttribute(String value) {
		return value == null || value.isEmpty() ? null : StatusPagamentoEnum.valueOf(value);
	}

}
