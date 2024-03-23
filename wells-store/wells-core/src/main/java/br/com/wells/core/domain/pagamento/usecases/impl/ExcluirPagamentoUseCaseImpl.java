package br.com.wells.core.domain.pagamento.usecases.impl;

import br.com.wells.core.domain.pagamento.gateways.ExcluirPagamentoGateway;
import br.com.wells.core.domain.pagamento.usecases.ExcluirPagamentoUseCase;

public class ExcluirPagamentoUseCaseImpl implements ExcluirPagamentoUseCase {

	private final ExcluirPagamentoGateway excluirPagamentoGateway;

	public ExcluirPagamentoUseCaseImpl(ExcluirPagamentoGateway excluirPagamentoGateway) {
		this.excluirPagamentoGateway = excluirPagamentoGateway;
	}

	public void execute(Long id) {
		excluirPagamentoGateway.execute(id);
	}

}
