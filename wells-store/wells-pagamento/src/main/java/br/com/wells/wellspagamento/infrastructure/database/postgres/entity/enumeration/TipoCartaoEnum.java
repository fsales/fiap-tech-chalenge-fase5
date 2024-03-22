package br.com.wells.wellspagamento.infrastructure.database.postgres.entity.enumeration;

import java.util.Arrays;
import java.util.Optional;

import br.com.wells.core.domain.pagamento.model.enumeration.ModalidadeCartao;
import br.com.wells.core.domain.pagamento.model.enumeration.TipoCartao;

public enum TipoCartaoEnum {

	VISA_CREDITO("Visa"), MASTERCARD_CREDITO("MasterCard"), AMEX_CREDITO("Amex"), ELO_CREDITO("Elo"),
	ALELO_VALE_REFEICAO("Alelo"), TICKET_RESTAURANTE("Ticket Restaurante"),
	MASTERCARD_MAESTRO_DEBITO("MasterCard Maestro"), VISA_DEBITO("Visa Débito"), ELO_DEBITO("Elo");

	private final String nomeCartao;

	TipoCartaoEnum(String nomeCartao) {
		this.nomeCartao = nomeCartao;
	}

	public static Optional<TipoCartaoEnum> fromSigla(String sigla) {
		return Arrays.stream(values()).filter(tipo -> tipo.name().equalsIgnoreCase(sigla)).findFirst();
	}

	public String getNomeCartao() {
		return nomeCartao;
	}

}
