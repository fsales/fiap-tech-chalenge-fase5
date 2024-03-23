package br.com.wells.core.domain.pagamento.usecases;

import br.com.wells.core.domain.pagamento.model.Pagamento;

public interface CriarPagamentoUseCase {

	Pagamento execute(final Pagamento pagamento);

}
