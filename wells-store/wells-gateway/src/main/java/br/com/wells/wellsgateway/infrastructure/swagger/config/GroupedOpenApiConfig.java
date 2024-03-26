package br.com.wells.wellsgateway.infrastructure.swagger.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GroupedOpenApiConfig {

	private static final String PATH_POSFIXO = "/**";

	@Bean
	public GroupedOpenApi actuatorApi() {

		String[] paths = { String.format("%s%s", "/actuator", PATH_POSFIXO) };
		return GroupedOpenApi.builder().group("Actuator").pathsToMatch(paths).build();
	}

}