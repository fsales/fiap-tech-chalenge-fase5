package br.com.wells.wellscarrinho.infrastructure.amqp.processor;

import br.com.wells.core.domain.pagamento.model.Pagamento;
import br.com.wells.wellscarrinho.infrastructure.amqp.constants.QueueNames;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class NotificarPagamentoConsumer {

	@RabbitListener(queues = QueueNames.NOTIFICAR_PAGAMENTOS)
	public void consumerNotificarPagamento(Pagamento pagamento) {
		log.info("Consumindo pagamento: {}", pagamento);
	}

}
