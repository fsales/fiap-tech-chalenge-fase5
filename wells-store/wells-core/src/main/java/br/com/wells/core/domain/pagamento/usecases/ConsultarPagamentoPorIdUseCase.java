package br.com.wells.core.domain.pagamento.usecases;

import br.com.wells.core.domain.pagamento.model.Pagamento;

public interface ConsultarPagamentoPorIdUseCase {

	Pagamento execute(Long id);

}
