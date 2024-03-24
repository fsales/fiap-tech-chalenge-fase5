package br.com.wells.core.domain.pagamento.usecases.validation;

import java.util.Objects;

import br.com.wells.core.domain.exception.WellsStoreValidationException;
import br.com.wells.core.domain.pagamento.model.Pagamento;

public final class PagamentoValidation {

	private PagamentoValidation() {
	}

	public static void validarCamposObrigatorios(Pagamento pagamento) {
		try {
			Objects.requireNonNull(pagamento.pedidoId(), "O campo 'pedidoId' é obrigatório.");

			camposObrigatorioComuns(pagamento);

			validarCampoNaoVazio(pagamento.nome(), "nome");
			validarCampoNaoVazio(pagamento.numero(), "numero");
			validarCampoNaoVazio(pagamento.codigo(), "codigo");

		}
		catch (NullPointerException e) {
			throw new WellsStoreValidationException(e.getMessage(), e);
		}
	}

	private static void camposObrigatorioComuns(Pagamento pagamento) {
		Objects.requireNonNull(pagamento.nome(), "O campo 'nome' é obrigatório.");
		Objects.requireNonNull(pagamento.numero(), "O campo 'numero' é obrigatório.");
		Objects.requireNonNull(pagamento.expiracao(), "O campo 'expiracao' é obrigatório.");
		Objects.requireNonNull(pagamento.codigo(), "O campo 'codigo' é obrigatório.");
		Objects.requireNonNull(pagamento.status(), "O campo 'status' é obrigatório.");
		Objects.requireNonNull(pagamento.tipoCartao(), "O campo 'tipoCartao' é obrigatório.");
	}

	public static void validarCamposObrigatoriosAtualizar(Pagamento pagamento) {
		try {
			Objects.requireNonNull(pagamento.id(), "O campo 'id' é obrigatório.");

			camposObrigatorioComuns(pagamento);

			validarCampoNaoVazio(pagamento.nome(), "nome");
			validarCampoNaoVazio(pagamento.numero(), "numero");
			validarCampoNaoVazio(pagamento.codigo(), "codigo");

		}
		catch (NullPointerException e) {
			throw new WellsStoreValidationException(e.getMessage(), e);
		}
	}

	private static void validarCampoNaoVazio(String valor, String nomeCampo) {
		if (valor.trim().isEmpty()) {
			throw new WellsStoreValidationException("O campo '" + nomeCampo + "' não pode estar em branco.");
		}
	}

}
