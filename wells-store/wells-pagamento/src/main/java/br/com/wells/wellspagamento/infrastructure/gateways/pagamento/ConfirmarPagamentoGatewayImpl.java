package br.com.wells.wellspagamento.infrastructure.gateways.pagamento;

import br.com.wells.core.domain.exception.WellsStoreEntityNotFoundException;
import br.com.wells.core.domain.exception.WellsStoreUniqueViolationException;
import br.com.wells.core.domain.pagamento.gateways.ConfirmarPagamentoGateway;
import br.com.wells.core.domain.pagamento.model.Pagamento;
import br.com.wells.wellspagamento.infrastructure.database.postgres.repository.PagamentoEntityRepository;
import br.com.wells.wellspagamento.infrastructure.gateways.pagamento.mapper.PagamentoEntityMapper;
import org.springframework.dao.DataIntegrityViolationException;

public class ConfirmarPagamentoGatewayImpl implements ConfirmarPagamentoGateway {

	private final PagamentoEntityRepository pagamentoEntityRepository;

	public ConfirmarPagamentoGatewayImpl(PagamentoEntityRepository pagamentoEntityRepository) {
		this.pagamentoEntityRepository = pagamentoEntityRepository;
	}

	@Override
	public Pagamento execute(Pagamento pagamento) {

		try {
			var pagamentaConfirmar = pagamentoEntityRepository.findById(pagamento.id())
				.orElseThrow(() -> new WellsStoreEntityNotFoundException("Pagamento não encontrado"));

			return PagamentoEntityMapper.convertToPagamento(pagamentoEntityRepository
				.save(PagamentoEntityMapper.update(pagamentaConfirmar, pagamento.confirmar())));
		}
		catch (DataIntegrityViolationException e) {
			throw new WellsStoreUniqueViolationException("Erro ao confirmar pagamento", e);
		}
	}

}
