package br.com.wells.wellspagamento.presentation.rest.controller.pagamento.dto.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.wells.wellspagamento.presentation.rest.controller.pagamento.dto.response.swagger.PagamentoResponseSwagger;

public record PagamentoResponse(Long id, BigDecimal valor, String nome, String numero, LocalDate expiracao,
		String codigo, Long pedidoId,

		String status,

		String tipoCartao) implements Serializable, PagamentoResponseSwagger {
}