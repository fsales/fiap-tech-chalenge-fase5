package br.com.wells.core.domain.pagamento.usecases.impl;

import br.com.wells.core.domain.pagamento.gateways.ConsultarTodosPagamentoGateway;
import br.com.wells.core.domain.pagamento.model.Pagamento;
import br.com.wells.core.domain.pagamento.usecases.ConsultarTodosPagamentoUseCase;
import br.com.wells.core.domain.page.Pagina;

public class ConsultarTodosPagamentoUseCaseImpl implements ConsultarTodosPagamentoUseCase {

	private final ConsultarTodosPagamentoGateway consultarTodosPagamentoGateway;

	public ConsultarTodosPagamentoUseCaseImpl(ConsultarTodosPagamentoGateway consultarTodosPagamentoGateway) {
		this.consultarTodosPagamentoGateway = consultarTodosPagamentoGateway;
	}

	@Override
	public Pagina<Pagamento> execute(int pageNumber, int pageSize) {
		return consultarTodosPagamentoGateway.execute(pageNumber, pageSize);
	}

}
