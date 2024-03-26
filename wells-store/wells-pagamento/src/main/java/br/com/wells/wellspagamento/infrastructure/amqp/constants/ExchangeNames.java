package br.com.wells.wellspagamento.infrastructure.amqp.constants;

public final class ExchangeNames {

	public static final String PAGAMENTOS_EX = "pagamentos.ex";

	public static final String PAGAMENTOS_DLX = "pagamentos.dlx";

	public static final String PAGAMENTOS_A_CONFIRMAR_EX = "pagamentos.a.confirmar.ex";

	public static final String PAGAMENTOS_A_CONFIRMAR_DLX = "pagamentos.a.confirmar.dlx";

	public static final String NOTIFICAR_PAGAMENTOS_EX = "notificar.pagamentos.ex";

	public static final String NOTIFICAR_PAGAMENTOS_DLX = "notificar.pagamentos.dlx";

	private ExchangeNames() {
	}

}
