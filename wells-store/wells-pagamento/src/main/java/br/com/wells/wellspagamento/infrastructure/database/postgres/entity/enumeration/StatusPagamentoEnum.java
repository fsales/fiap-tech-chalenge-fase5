package br.com.wells.wellspagamento.infrastructure.database.postgres.entity.enumeration;

import java.util.Arrays;

public enum StatusPagamentoEnum {

	CRIADO("Criado"), CONFIRMADO("Confirmado"), CANCELADO("Cancelado");

	private final String descricao;

	StatusPagamentoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public static StatusPagamentoEnum fromString(String texto) {
		return Arrays.stream(values())
			.filter(status -> status.descricao.equalsIgnoreCase(texto))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(
					"Nenhum status de pagamento encontrado com a descrição fornecida: " + texto));
	}

}
