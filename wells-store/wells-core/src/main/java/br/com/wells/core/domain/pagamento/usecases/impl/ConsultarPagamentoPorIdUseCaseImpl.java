package br.com.wells.core.domain.pagamento.usecases.impl;

import br.com.wells.core.domain.pagamento.gateways.ConsultarPagamentoPorIdGateway;
import br.com.wells.core.domain.pagamento.model.Pagamento;
import br.com.wells.core.domain.pagamento.usecases.ConsultarPagamentoPorIdUseCase;
import br.com.wells.core.domain.exception.WellsStoreEntityNotFoundException;

public class ConsultarPagamentoPorIdUseCaseImpl implements ConsultarPagamentoPorIdUseCase {

	private final ConsultarPagamentoPorIdGateway consultarPagamentoPorIdGateway;

	public ConsultarPagamentoPorIdUseCaseImpl(ConsultarPagamentoPorIdGateway consultarPagamentoPorIdGateway) {
		this.consultarPagamentoPorIdGateway = consultarPagamentoPorIdGateway;
	}

	@Override
	public Pagamento execute(Long id) {
		return consultarPagamentoPorIdGateway.execute(id)
			.orElseThrow(() -> new WellsStoreEntityNotFoundException(
					String.format("Pagamento de id=%s não encontrado", id)));
	}

}
