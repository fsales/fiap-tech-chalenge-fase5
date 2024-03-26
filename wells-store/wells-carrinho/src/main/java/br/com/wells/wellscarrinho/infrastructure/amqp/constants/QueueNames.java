package br.com.wells.wellscarrinho.infrastructure.amqp.constants;

public final class QueueNames {

	public static final String PAGAMENTOS_PEDIDO = "pagamentos.pedido";

	public static final String PAGAMENTOS_PEDIDO_DLQ = "pagamentos.pedido.dlq";

	public static final String PAGAMENTOS_A_CONFIRMAR = "pagamentos.a.confirmar";

	public static final String PAGAMENTOS_A_CONFIRMAR_DLQ = "pagamentos.a.confirmar.dlq";

	public static final String NOTIFICAR_PAGAMENTOS = "notificar.pagamentos";

	public static final String NOTIFICAR_PAGAMENTOS_DLQ = "notificar.pagamentos.dlq";

	private QueueNames() {
	}

}
