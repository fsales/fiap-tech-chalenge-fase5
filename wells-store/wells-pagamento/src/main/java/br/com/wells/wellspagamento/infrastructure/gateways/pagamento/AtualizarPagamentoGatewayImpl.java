package br.com.wells.wellspagamento.infrastructure.gateways.pagamento;

import br.com.wells.core.domain.pagamento.gateways.AtualizarPagamentoGateway;
import br.com.wells.wellspagamento.infrastructure.database.postgres.repository.PagamentoEntityRepository;

public class AtualizarPagamentoGatewayImpl implements AtualizarPagamentoGateway {

	private final PagamentoEntityRepository pagamentoEntityRepository;

	public AtualizarPagamentoGatewayImpl(PagamentoEntityRepository pagamentoEntityRepository) {
		this.pagamentoEntityRepository = pagamentoEntityRepository;
	}

}
