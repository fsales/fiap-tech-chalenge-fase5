package br.com.wells.wellspagamento.infrastructure.gateways.pedido;

import br.com.wells.core.domain.pedido.gateways.ConfirmarPagamentoPedido;

public class ConfirmarPagamentoPedidoImpl implements ConfirmarPagamentoPedido {

	@Override
	public void execute(Long id) {
		System.out.println("ConfirmarPagamentoPedidoImpl.execute" + id);
	}

}
