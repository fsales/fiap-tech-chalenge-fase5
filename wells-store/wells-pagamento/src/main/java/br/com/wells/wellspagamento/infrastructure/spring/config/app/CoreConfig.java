package br.com.wells.wellspagamento.infrastructure.spring.config.app;

import br.com.wells.core.domain.pagamento.usecases.AlteraStatusPagamentoUseCase;
import br.com.wells.core.domain.pagamento.usecases.AtualizarPagamentoUseCase;
import br.com.wells.core.domain.pagamento.usecases.ConfirmarPagamentoUseCase;
import br.com.wells.core.domain.pagamento.usecases.ConsultarPagamentoPorIdUseCase;
import br.com.wells.core.domain.pagamento.usecases.ConsultarTodosPagamentoUseCase;
import br.com.wells.core.domain.pagamento.usecases.CriarPagamentoUseCase;
import br.com.wells.core.domain.pagamento.usecases.ExcluirPagamentoUseCase;
import br.com.wells.core.domain.pagamento.usecases.impl.AlteraStatusPagamentoUseCaseImpl;
import br.com.wells.core.domain.pagamento.usecases.impl.AtualizarPagamentoUseCaseImpl;
import br.com.wells.core.domain.pagamento.usecases.impl.ConfirmarPagamentoUseCaseImpl;
import br.com.wells.core.domain.pagamento.usecases.impl.ConsultarPagamentoPorIdUseCaseImpl;
import br.com.wells.core.domain.pagamento.usecases.impl.ConsultarTodosPagamentoUseCaseImpl;
import br.com.wells.core.domain.pagamento.usecases.impl.CriarPagamentoUseCaseImpl;
import br.com.wells.core.domain.pagamento.usecases.impl.ExcluirPagamentoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreConfig {

	@Bean
	AlteraStatusPagamentoUseCase alteraStatusPagamentoUseCase() {
		return new AlteraStatusPagamentoUseCaseImpl();
	}

	@Bean
	AtualizarPagamentoUseCase atualizarPagamentoUseCase() {
		return new AtualizarPagamentoUseCaseImpl();
	}

	@Bean
	ConfirmarPagamentoUseCase confirmarPagamentoUseCase() {
		return new ConfirmarPagamentoUseCaseImpl();
	}

	@Bean
	ConsultarPagamentoPorIdUseCase consultarPagamentoPorIdUseCase() {
		return new ConsultarPagamentoPorIdUseCaseImpl();
	}

	@Bean
	ConsultarTodosPagamentoUseCase consultarTodosPagamentoUseCase() {
		return new ConsultarTodosPagamentoUseCaseImpl();
	}

	@Bean
	CriarPagamentoUseCase criarPagamentoUseCase() {
		return new CriarPagamentoUseCaseImpl();
	}

	@Bean
	ExcluirPagamentoUseCase excluirPagamentoUseCase() {
		return new ExcluirPagamentoUseCaseImpl();
	}

}
