package br.com.wells.app.infrastructure.config.swagger;

import br.com.wells.app.infrastructure.config.spring.config.app.ApiRoutes;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class GroupedOpenApiConfig {

	private static final String PATH_POSFIXO = "/**";

	@Bean
	public GroupedOpenApi actuatorApi() {

		String[] paths = { String.format("%s%s", ApiRoutes.ACTUATOR, PATH_POSFIXO) };
		return GroupedOpenApi.builder().group("Actuator").pathsToMatch(paths).build();
	}

	@Bean
	GroupedOpenApi usuarioOpenApi() {
		String[] paths = { String.format("/%s%s", ApiRoutes.USUARIOS_URI, PATH_POSFIXO) };
		return GroupedOpenApi.builder().group("Usuario").pathsToMatch(paths).build();
	}

	@Bean
	GroupedOpenApi authOpenApi() {
		String[] paths = { String.format("/%s%s", ApiRoutes.AUTH_URI, PATH_POSFIXO) };
		return GroupedOpenApi.builder().group("Auth").pathsToMatch(paths).build();
	}

}