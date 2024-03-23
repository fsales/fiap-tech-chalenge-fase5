package br.com.wells.wellspagamento.infrastructure.gateways.pagamento.mapper;

import br.com.wells.core.domain.pagamento.model.Pagamento;
import br.com.wells.core.domain.pagamento.model.enumeration.StatusPagamento;
import br.com.wells.core.domain.pagamento.model.enumeration.TipoCartao;
import br.com.wells.wellspagamento.infrastructure.database.postgres.entity.PagamentoEntity;
import br.com.wells.wellspagamento.infrastructure.database.postgres.entity.enumeration.StatusPagamentoEnum;
import br.com.wells.wellspagamento.infrastructure.database.postgres.entity.enumeration.TipoCartaoEnum;

public class PagamentoEntityMapper {

	private PagamentoEntityMapper() {
	}

	public static PagamentoEntity convetToPagamentoEntity(final Pagamento pagamento) {
		return PagamentoEntity.builder()
			.codigo(pagamento.codigo())
			.nome(pagamento.nome())
			.valor(pagamento.valor())
			.numero(pagamento.numero())
			.expiracao(pagamento.expiracao())
			.pedidoId(pagamento.pedidoId())
			.status(StatusPagamentoEnum.valueOf(pagamento.status().name()))
			.tipoCartao(TipoCartaoEnum.valueOf(pagamento.tipoCartao().name()))
			.build();
	}

	public static Pagamento convertToPagamento(final PagamentoEntity pagamentoEntity) {
		return new Pagamento(pagamentoEntity.getId(), pagamentoEntity.getValor(), pagamentoEntity.getNome(),
				pagamentoEntity.getNumero(), pagamentoEntity.getExpiracao(), pagamentoEntity.getCodigo(),
				StatusPagamento.valueOf(pagamentoEntity.getStatus().name()),
				TipoCartao.valueOf(pagamentoEntity.getTipoCartao().name()), pagamentoEntity.getPedidoId());
	}

	public static PagamentoEntity update(final PagamentoEntity pagamentoEntity, final Pagamento pagamento) {
		pagamentoEntity.setCodigo(pagamento.codigo());
		pagamentoEntity.setNome(pagamento.nome());
		pagamentoEntity.setNumero(pagamento.numero());
		pagamentoEntity.setExpiracao(pagamento.expiracao());
		pagamentoEntity.setValor(pagamento.valor());
		pagamentoEntity.setPedidoId(pagamento.pedidoId());
		pagamentoEntity.setStatus(StatusPagamentoEnum.valueOf(pagamento.status().name()));
		pagamentoEntity.setTipoCartao(TipoCartaoEnum.valueOf(pagamento.tipoCartao().name()));
		return pagamentoEntity;
	}

}
