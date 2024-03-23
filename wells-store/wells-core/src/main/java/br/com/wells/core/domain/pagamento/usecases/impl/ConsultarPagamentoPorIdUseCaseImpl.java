package br.com.wells.core.domain.pagamento.usecases.impl;

import br.com.wells.core.domain.pagamento.gateways.ConsultarPagamentoPorIdGateway;
import br.com.wells.core.domain.pagamento.model.Pagamento;
import br.com.wells.core.domain.pagamento.usecases.ConsultarPagamentoPorIdUseCase;
import br.com.wells.core.domain.usuario.exception.EntityNotFoundException;

public class ConsultarPagamentoPorIdUseCaseImpl implements ConsultarPagamentoPorIdUseCase {

	private ConsultarPagamentoPorIdGateway consultarPagamentoPorIdGateway;

	@Override
	public Pagamento execute(Long id) {
		return consultarPagamentoPorIdGateway.execute(id)
			.orElseThrow(() -> new EntityNotFoundException(String.format("Pagamento de id=%s não encontrado", id)));
	}

}
