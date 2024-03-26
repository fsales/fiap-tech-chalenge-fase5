package br.com.wells.wellspagamento.infrastructure.database.postgres.entity.enumeration.converter;

import br.com.wells.wellspagamento.infrastructure.database.postgres.entity.enumeration.TipoCartaoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoCartaoConverter implements AttributeConverter<TipoCartaoEnum, String> {

	@Override
	public String convertToDatabaseColumn(TipoCartaoEnum tipoCartaoEnum) {
		return tipoCartaoEnum == null ? null : tipoCartaoEnum.name();
	}

	@Override
	public TipoCartaoEnum convertToEntityAttribute(String value) {
		return value == null || value.isEmpty() ? null : TipoCartaoEnum.valueOf(value);
	}

}
