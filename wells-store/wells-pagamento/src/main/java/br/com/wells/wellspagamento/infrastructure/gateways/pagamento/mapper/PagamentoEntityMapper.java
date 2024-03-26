package br.com.wells.wellspagamento.infrastructure.gateways.pagamento.mapper;

import java.util.Objects;

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
		if (pagamento == null || pagamentoEntity == null) {
			return pagamentoEntity;
		}

		if (Objects.nonNull(pagamento.codigo()) && !Objects.equals(pagamento.codigo(), pagamentoEntity.getCodigo())) {
			pagamentoEntity.setCodigo(pagamento.codigo());
		}
		if (Objects.nonNull(pagamento.nome()) && !Objects.equals(pagamento.nome(), pagamentoEntity.getNome())) {
			pagamentoEntity.setNome(pagamento.nome());
		}
		if (Objects.nonNull(pagamento.numero()) && !Objects.equals(pagamento.numero(), pagamentoEntity.getNumero())) {
			pagamentoEntity.setNumero(pagamento.numero());
		}
		if (Objects.nonNull(pagamento.expiracao())
				&& !Objects.equals(pagamento.expiracao(), pagamentoEntity.getExpiracao())) {
			pagamentoEntity.setExpiracao(pagamento.expiracao());
		}
		if (Objects.nonNull(pagamento.valor()) && !Objects.equals(pagamento.valor(), pagamentoEntity.getValor())) {
			pagamentoEntity.setValor(pagamento.valor());
		}
		if (Objects.nonNull(pagamento.pedidoId())
				&& !Objects.equals(pagamento.pedidoId(), pagamentoEntity.getPedidoId())) {
			pagamentoEntity.setPedidoId(pagamento.pedidoId());
		}

		if (pagamento.status() != null) {
			pagamentoEntity.setStatus(StatusPagamentoEnum.valueOf(pagamento.status().name()));
		}
		if (pagamento.tipoCartao() != null) {
			pagamentoEntity.setTipoCartao(TipoCartaoEnum.valueOf(pagamento.tipoCartao().name()));
		}

		return pagamentoEntity;
	}

}
