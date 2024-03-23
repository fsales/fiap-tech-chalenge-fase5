package br.com.wells.wellspagamento.infrastructure.gateways.pagamento;

import java.util.Optional;

import br.com.wells.core.domain.pagamento.gateways.ConsultarPagamentoPorIdGateway;
import br.com.wells.core.domain.pagamento.model.Pagamento;
import br.com.wells.wellspagamento.infrastructure.database.postgres.repository.PagamentoEntityRepository;
import br.com.wells.wellspagamento.infrastructure.gateways.pagamento.mapper.PagamentoEntityMapper;

public class ConsultarPagamentoPorIdGatewayImpl implements ConsultarPagamentoPorIdGateway {

	private final PagamentoEntityRepository pagamentoEntityRepository;

	public ConsultarPagamentoPorIdGatewayImpl(PagamentoEntityRepository pagamentoEntityRepository) {
		this.pagamentoEntityRepository = pagamentoEntityRepository;
	}

	@Override
	public Optional<Pagamento> execute(Long id) {
		return pagamentoEntityRepository.findById(id).map(PagamentoEntityMapper::convertToPagamento);
	}

}
