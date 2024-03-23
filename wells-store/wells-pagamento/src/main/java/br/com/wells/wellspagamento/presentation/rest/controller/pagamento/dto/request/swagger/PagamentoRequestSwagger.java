package br.com.wells.wellspagamento.presentation.rest.controller.pagamento.dto.request.swagger;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface PagamentoRequestSwagger {

	BigDecimal valor();

	String nome();

	String numero();

	LocalDate expiracao();

	String codigo();

	Long pedidoId();

	String status();

	String tipoCartao();

}
