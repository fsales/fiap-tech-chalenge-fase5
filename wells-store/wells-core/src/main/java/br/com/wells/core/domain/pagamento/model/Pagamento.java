package br.com.wells.core.domain.pagamento.model;

import static br.com.wells.core.domain.pagamento.usecases.validation.PagamentoValidation.validarCamposObrigatorios;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import br.com.wells.core.domain.pagamento.model.enumeration.StatusPagamento;
import br.com.wells.core.domain.pagamento.model.enumeration.TipoCartao;

public record Pagamento(Long id, BigDecimal valor, String nome, String numero, LocalDate expiracao, String codigo,
		StatusPagamento status, TipoCartao tipoCartao, Long pedidoId) {

	public Pagamento(BigDecimal valor, String nome, String numero, LocalDate expiracao, String codigo, String status,
			String tipoCartao, Long pedidoId) {
		this(null, valor, nome, numero, expiracao, codigo, StatusPagamento.fromString(status),
				TipoCartao.fromNomeCartao(tipoCartao), pedidoId);

		validarCamposObrigatorios(this);
	}

	public Pagamento(Long id) {
		this(id, null, null, null, null, null, null, null, null);
	}

	public static Pagamento criar(BigDecimal valor, String nome, String numero, LocalDate expiracao, String codigo,
			String tipoCartao, Long pedidoId) {

		var pag = new Pagamento(null, valor, nome, numero, expiracao, codigo, StatusPagamento.CRIADO,
				TipoCartao.fromNomeCartao(tipoCartao), pedidoId);
		validarCamposObrigatorios(pag);
		return pag;

	}

	public Pagamento confirmar() {
		Objects.requireNonNull(id, "O campo 'id' é obrigatório.");
		return new Pagamento(id, valor, nome, numero, expiracao, codigo, StatusPagamento.CONFIRMADO, tipoCartao,
				pedidoId);
	}

	public Pagamento cancelar() {
		Objects.requireNonNull(id, "O campo 'id' é obrigatório.");
		return new Pagamento(id, valor, nome, numero, expiracao, codigo, StatusPagamento.CANCELADO, tipoCartao,
				pedidoId);
	}

	public boolean isConfirmado() {
		return StatusPagamento.CONFIRMADO.equals(status);
	}

	public boolean isCancelado() {
		return StatusPagamento.CANCELADO.equals(status);
	}
}
