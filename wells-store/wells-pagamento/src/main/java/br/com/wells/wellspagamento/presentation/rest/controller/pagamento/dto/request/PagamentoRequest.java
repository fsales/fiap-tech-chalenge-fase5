package br.com.wells.wellspagamento.presentation.rest.controller.pagamento.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.wells.wellspagamento.presentation.rest.controller.generic.validation.CreateInfo;
import br.com.wells.wellspagamento.presentation.rest.controller.generic.validation.UpdateInfo;
import br.com.wells.wellspagamento.presentation.rest.controller.pagamento.dto.request.swagger.PagamentoRequestSwagger;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record PagamentoRequest(@NotNull(message = "Valor não pode ser nulo", groups = {
		CreateInfo.class, UpdateInfo.class }) @Positive(message = "Valor deve ser positivo",
				groups = { CreateInfo.class, UpdateInfo.class }) BigDecimal valor,

		@NotNull(message = "Nome não pode ser nulo", groups = { CreateInfo.class, UpdateInfo.class }) @Size(max = 255,
				message = "O comprimento do nome deve ser menor ou igual a 255",
				groups = { CreateInfo.class, UpdateInfo.class }) String nome,

		@NotNull(message = "Número não pode ser nulo", groups = { CreateInfo.class, UpdateInfo.class }) @Pattern(
				regexp = "^[0-9]*$", message = "Número deve conter apenas dígitos",
				groups = { CreateInfo.class, UpdateInfo.class }) @Size(max = 19,
						message = "O comprimento do número deve ser menor ou igual a 19",
						groups = { CreateInfo.class, UpdateInfo.class }) String numero,

		@Future(message = "Expiração deve ser no futuro") @NotNull(message = "Expiração não pode ser nula",
				groups = { CreateInfo.class, UpdateInfo.class }) LocalDate expiracao,

		@NotNull(message = "Código não pode ser nulo", groups = { CreateInfo.class, UpdateInfo.class }) @Size(max = 3,
				message = "O comprimento do código deve ser menor ou igual a 3",
				groups = { CreateInfo.class, UpdateInfo.class }) String codigo,

		@NotNull(message = "PedidoId não pode ser nulo", groups = { CreateInfo.class, UpdateInfo.class }) Long pedidoId,

		@NotEmpty(message = "Status não pode estar vazio",
				groups = { CreateInfo.class, UpdateInfo.class }) String status,

		@NotNull(message = "TipoCartao não pode ser nulo",
				groups = { CreateInfo.class, UpdateInfo.class }) String tipoCartao)
		implements
			Serializable,
			PagamentoRequestSwagger{
}