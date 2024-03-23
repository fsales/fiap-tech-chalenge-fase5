package br.com.wells.wellspagamento.infrastructure.gateways.pagamento;

import br.com.wells.core.domain.pagamento.gateways.ConsultarTodosPagamentoGateway;
import br.com.wells.wellspagamento.infrastructure.database.postgres.repository.PagamentoEntityRepository;

public class ConsultarTodosPagamentoGatewayImpl implements ConsultarTodosPagamentoGateway {

	private final PagamentoEntityRepository pagamentoEntityRepository;

	public ConsultarTodosPagamentoGatewayImpl(PagamentoEntityRepository pagamentoEntityRepository) {
		this.pagamentoEntityRepository = pagamentoEntityRepository;
	}

}
