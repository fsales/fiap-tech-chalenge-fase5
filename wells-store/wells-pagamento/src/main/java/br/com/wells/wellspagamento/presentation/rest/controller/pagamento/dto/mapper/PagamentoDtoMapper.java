package br.com.wells.wellspagamento.presentation.rest.controller.pagamento.dto.mapper;

import br.com.wells.core.domain.pagamento.model.Pagamento;
import br.com.wells.wellspagamento.presentation.rest.controller.pagamento.dto.request.PagamentoRequest;
import br.com.wells.wellspagamento.presentation.rest.controller.pagamento.dto.response.PagamentoResponse;

public class PagamentoDtoMapper {

	private PagamentoDtoMapper() {
	}

	public static PagamentoResponse toPagamentoResponse(Pagamento pagamento) {
		return new PagamentoResponse(pagamento.id(), pagamento.valor(), pagamento.nome(), pagamento.numero(),
				pagamento.expiracao(), pagamento.codigo(), pagamento.pedidoId(), pagamento.status().getDescricao(),
				pagamento.tipoCartao().getNomeCartao());

	}

	public static Pagamento toPagamento(PagamentoRequest pagamentoRequest) {
		return new Pagamento(pagamentoRequest.valor(), pagamentoRequest.nome(), pagamentoRequest.numero(),
				pagamentoRequest.expiracao(), pagamentoRequest.codigo(), pagamentoRequest.status(),
				pagamentoRequest.tipoCartao(), pagamentoRequest.pedidoId()

		);
	}

}
