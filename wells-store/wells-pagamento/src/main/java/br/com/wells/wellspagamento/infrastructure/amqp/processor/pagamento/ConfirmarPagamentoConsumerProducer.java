package br.com.wells.wellspagamento.infrastructure.amqp.processor.pagamento;

import br.com.wells.core.domain.pagamento.model.Pagamento;
import br.com.wells.core.domain.pagamento.usecases.ConfirmarPagamentoUseCase;
import br.com.wells.core.domain.pedido.gateways.ConfirmarPagamentoPedidoGateway;
import br.com.wells.wellspagamento.infrastructure.amqp.constants.QueueNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConfirmarPagamentoConsumerProducer implements ConfirmarPagamentoPedidoGateway {

	private final ConfirmarPagamentoUseCase confirmarPagamentoUseCase;

	private final RabbitTemplate rabbitTemplate;

	public ConfirmarPagamentoConsumerProducer(ConfirmarPagamentoUseCase confirmarPagamentoUseCase,
			RabbitTemplate rabbitTemplate) {
		this.confirmarPagamentoUseCase = confirmarPagamentoUseCase;
		this.rabbitTemplate = rabbitTemplate;
	}

	@RabbitListener(queues = QueueNames.PAGAMENTOS_A_CONFIRMAR)
	public void consumeAndProducePagamentoAConfirmar(Pagamento pagamento) {
		var pagamentoConfirmado = execute(pagamento);

		rabbitTemplate.convertAndSend(QueueNames.NOTIFICAR_PAGAMENTOS, pagamentoConfirmado);
	}

	@Override
	public Pagamento execute(Pagamento pagamento) {
		log.info("Confirmando pagamento: {}", pagamento);
		return confirmarPagamentoUseCase.execute(pagamento.id());

	}

}
