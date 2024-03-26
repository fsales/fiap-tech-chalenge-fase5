package br.com.wells.wellspagamento.infrastructure.amqp.processor.pagamento;

import br.com.wells.core.domain.pagamento.model.Pagamento;
import br.com.wells.core.domain.pagamento.usecases.CriarPagamentoUseCase;
import br.com.wells.wellspagamento.infrastructure.amqp.constants.QueueNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CriarPagamentoConsumerProducer implements CriarPagamentoUseCase {

	private final CriarPagamentoUseCase criarPagamentoUseCase;

	private final RabbitTemplate rabbitTemplate;

	public CriarPagamentoConsumerProducer(CriarPagamentoUseCase criarPagamentoUseCase, RabbitTemplate rabbitTemplate) {
		this.criarPagamentoUseCase = criarPagamentoUseCase;
		this.rabbitTemplate = rabbitTemplate;
	}

	@RabbitListener(queues = QueueNames.PAGAMENTOS_PEDIDO)
	public void consumeAndProducePagamento(Pagamento pagamento) {
		var pargamentoCriado = execute(pagamento);

		rabbitTemplate.convertAndSend(QueueNames.PAGAMENTOS_A_CONFIRMAR, pargamentoCriado);
	}

	@Override
	public Pagamento execute(Pagamento pagamento) {
		log.info("Consumindo pagamento: {}", pagamento);
		return criarPagamentoUseCase.execute(pagamento);
	}

}