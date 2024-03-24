package br.com.wells.wellspagamento.infrastructure.gateways.pagamento;

import br.com.wells.core.domain.exception.WellsStoreEntityNotFoundException;
import br.com.wells.core.domain.exception.WellsStoreUniqueViolationException;
import br.com.wells.core.domain.pagamento.gateways.CancelarPagamentoGateway;
import br.com.wells.core.domain.pagamento.model.Pagamento;
import br.com.wells.wellspagamento.infrastructure.database.postgres.repository.PagamentoEntityRepository;
import br.com.wells.wellspagamento.infrastructure.gateways.pagamento.mapper.PagamentoEntityMapper;
import org.springframework.dao.DataIntegrityViolationException;

public class CancelarPagamentoGatewayImpl implements CancelarPagamentoGateway {

	private final PagamentoEntityRepository pagamentoEntityRepository;

	public CancelarPagamentoGatewayImpl(PagamentoEntityRepository pagamentoEntityRepository) {
		this.pagamentoEntityRepository = pagamentoEntityRepository;
	}

	@Override
	public Pagamento execute(final Pagamento pagamento) {

		try {
			var entity = pagamentoEntityRepository.findById(pagamento.id())
				.orElseThrow(() -> new WellsStoreEntityNotFoundException("Pagamento não encontrado"));

			return PagamentoEntityMapper.convertToPagamento(
					pagamentoEntityRepository.save(PagamentoEntityMapper.update(entity, pagamento.cancelar())));
		}
		catch (DataIntegrityViolationException e) {
			throw new WellsStoreUniqueViolationException("Pagamento não pode ser cancelado.", e);
		}
	}

}
