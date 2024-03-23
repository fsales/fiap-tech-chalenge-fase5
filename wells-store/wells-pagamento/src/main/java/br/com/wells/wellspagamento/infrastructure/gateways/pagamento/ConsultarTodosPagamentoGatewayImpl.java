package br.com.wells.wellspagamento.infrastructure.gateways.pagamento;

import br.com.wells.core.domain.pagamento.gateways.ConsultarTodosPagamentoGateway;
import br.com.wells.core.domain.pagamento.model.Pagamento;
import br.com.wells.core.domain.page.Pagina;
import br.com.wells.wellspagamento.infrastructure.database.postgres.repository.PagamentoEntityRepository;
import br.com.wells.wellspagamento.infrastructure.gateways.pagamento.mapper.PagamentoEntityMapper;
import org.springframework.data.domain.Pageable;

public class ConsultarTodosPagamentoGatewayImpl implements ConsultarTodosPagamentoGateway {

	private final PagamentoEntityRepository pagamentoEntityRepository;

	public ConsultarTodosPagamentoGatewayImpl(PagamentoEntityRepository pagamentoEntityRepository) {
		this.pagamentoEntityRepository = pagamentoEntityRepository;
	}

	@Override
	public Pagina<Pagamento> execute(final int pageNumber, final int pageSize) {
		Pageable pageable = Pageable.ofSize(pageSize).withPage(pageNumber);

		var pagamentoPage = pagamentoEntityRepository.findAll(pageable);

		return new Pagina<>(pagamentoPage.getContent(), pagamentoPage.getTotalElements())
			.map(PagamentoEntityMapper::convertToPagamento);
	}

}
