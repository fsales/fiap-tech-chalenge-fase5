package br.com.wells.core.domain.pagamento.model;

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

	private static void validarCamposObrigatorios(Pagamento pagamento) {
		Objects.requireNonNull(pagamento.nome(), "O campo 'nome' é obrigatório.");
		Objects.requireNonNull(pagamento.numero(), "O campo 'numero' é obrigatório.");
		Objects.requireNonNull(pagamento.expiracao(), "O campo 'expiracao' é obrigatório.");
		Objects.requireNonNull(pagamento.codigo(), "O campo 'codigo' é obrigatório.");
		Objects.requireNonNull(pagamento.status(), "O campo 'status' é obrigatório.");
		Objects.requireNonNull(pagamento.tipoCartao(), "O campo 'tipoCartao' é obrigatório.");
		Objects.requireNonNull(pagamento.pedidoId(), "O campo 'pedidoId' é obrigatório.");

		validarCampoNaoVazio(pagamento.nome(), "nome");
		validarCampoNaoVazio(pagamento.numero(), "numero");
		validarCampoNaoVazio(pagamento.codigo(), "codigo");
	}

	public static Pagamento cadastar(BigDecimal valor, String nome, String numero, LocalDate expiracao, String codigo,
			String tipoCartao, Long pedidoId) {

		var pag = new Pagamento(null, valor, nome, numero, expiracao, codigo, StatusPagamento.CRIADO,
				TipoCartao.fromNomeCartao(tipoCartao), pedidoId);
		validarCamposObrigatorios(pag);
		return pag;

	}

	private static void validarCampoNaoVazio(String valor, String nomeCampo) {
		if (valor.trim().isEmpty()) {
			throw new IllegalArgumentException("O campo '" + nomeCampo + "' não pode estar em branco.");
		}
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
}
