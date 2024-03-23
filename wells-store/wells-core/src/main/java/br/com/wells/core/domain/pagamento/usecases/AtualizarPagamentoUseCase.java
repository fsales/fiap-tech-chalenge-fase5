package br.com.wells.core.domain.pagamento.usecases;

import br.com.wells.core.domain.pagamento.model.Pagamento;

public interface AtualizarPagamentoUseCase {

	Pagamento execute(final Long id, final Pagamento pagamento);

}
