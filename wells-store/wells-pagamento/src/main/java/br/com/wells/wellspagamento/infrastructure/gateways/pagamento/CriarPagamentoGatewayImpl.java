package br.com.wells.wellspagamento.infrastructure.gateways.pagamento;

import static br.com.wells.wellspagamento.infrastructure.gateways.pagamento.mapper.PagamentoEntityMapper.convertToPagamento;
import static br.com.wells.wellspagamento.infrastructure.gateways.pagamento.mapper.PagamentoEntityMapper.convetToPagamentoEntity;

import br.com.wells.core.domain.exception.WellsStoreUniqueViolationException;
import br.com.wells.core.domain.pagamento.gateways.CriarPagamentoGateway;
import br.com.wells.core.domain.pagamento.model.Pagamento;
import br.com.wells.wellspagamento.infrastructure.database.postgres.repository.PagamentoEntityRepository;
import org.springframework.dao.DataIntegrityViolationException;

public class CriarPagamentoGatewayImpl implements CriarPagamentoGateway {

	private final PagamentoEntityRepository pagamentoEntityRepository;

	public CriarPagamentoGatewayImpl(PagamentoEntityRepository pagamentoEntityRepository) {
		this.pagamentoEntityRepository = pagamentoEntityRepository;
	}

	@Override
	public Pagamento execute(final Pagamento pagamento) {
		try {
			var pagamentoEntity = pagamentoEntityRepository.save(convetToPagamentoEntity(pagamento));

			return convertToPagamento(pagamentoEntity);
		}
		catch (DataIntegrityViolationException e) {
			throw new WellsStoreUniqueViolationException("Erro ao salvar pagamento", e);
		}
	}

}
