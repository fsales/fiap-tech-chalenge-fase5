package br.com.wells.wellspagamento.presentation.rest.controller.pagamento.dto.request.swagger;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Detalhes sobre a requisição de pagamento")
public interface PagamentoRequestSwagger {

	@Schema(description = "Valor do pagamento", example = "100.00")
	BigDecimal valor();

	@Schema(description = "Nome do titular do cartão", example = "Nome do Titular")
	String nome();

	@Schema(description = "Número do cartão", example = "1234567890123456")
	String numero();

	@Schema(description = "Data de expiração do cartão", example = "2023-12-31")
	LocalDate expiracao();

	@Schema(description = "Código de segurança do cartão", example = "123")
	String codigo();

	@Schema(description = "ID do pedido", example = "1")
	default Long pedidoId() {
		return null;
	};

	@Schema(description = "Status do pagamento", example = "Criado", allowableValues = "Criado, Confirmado, Cancelado")
	String status();

	@Schema(description = "Tipo do cartão", example = "Visa",
			allowableValues = "MasterCard, Amex, Alelo,Ticket Restaurante, MasterCard Maestro, Visa Débito, Elo, Elo Débito")
	String tipoCartao();

}