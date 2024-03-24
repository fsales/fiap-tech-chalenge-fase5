package br.com.wells.wellspagamento.presentation.rest.controller.pagamento.dto.mapper;

import br.com.wells.core.domain.pagamento.model.Pagamento;
import br.com.wells.wellspagamento.presentation.rest.controller.pagamento.dto.request.AtualizarPagamentoRequest;
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

	public static Pagamento cadastar(PagamentoRequest pagamentoRequest) {

		return Pagamento.criar(pagamentoRequest.valor(), pagamentoRequest.nome(), pagamentoRequest.numero(),
				pagamentoRequest.expiracao(), pagamentoRequest.codigo(), pagamentoRequest.tipoCartao(),
				pagamentoRequest.pedidoId()

		);
	}

	public static Pagamento toPagamento(AtualizarPagamentoRequest pagamentoRequest) {
		return new Pagamento(pagamentoRequest.valor(), pagamentoRequest.nome(), pagamentoRequest.numero(),
				pagamentoRequest.expiracao(), pagamentoRequest.codigo(), pagamentoRequest.status(),
				pagamentoRequest.tipoCartao(), pagamentoRequest.pedidoId()

		);
	}

}
