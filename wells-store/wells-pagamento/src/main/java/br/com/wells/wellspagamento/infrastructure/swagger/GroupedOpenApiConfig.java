package br.com.wells.wellspagamento.infrastructure.swagger;

import br.com.wells.wellspagamento.infrastructure.spring.config.app.ApiRoutes;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class GroupedOpenApiConfig {

	private static final String PATH_POSFIXO = "/**";

	@Bean
	GroupedOpenApi actuatorApi() {

		String[] paths = { String.format("%s%s", ApiRoutes.ACTUATOR, PATH_POSFIXO) };
		return GroupedOpenApi.builder().group("Actuator").pathsToMatch(paths).build();
	}

	@Bean
	GroupedOpenApi pagamentoApi() {

		String[] paths = { String.format("%s%s", ApiRoutes.PAGAMENTO_URI, PATH_POSFIXO) };
		return GroupedOpenApi.builder().group("Pagamento").pathsToMatch(paths).build();
	}
}