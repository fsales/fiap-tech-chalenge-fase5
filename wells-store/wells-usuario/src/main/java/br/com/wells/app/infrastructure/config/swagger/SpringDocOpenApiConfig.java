package br.com.wells.app.infrastructure.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI().components(new Components().addSecuritySchemes("security", securityScheme()))
			.info(new Info().title("REST API - Wells Store")
				.description("API para gestão usuários")
				.version("v1")
				.license(new License().name("MIT License")));
	}

	private SecurityScheme securityScheme() {
		return new SecurityScheme().description("Insira um bearer token valido para prosseguir")
			.type(SecurityScheme.Type.HTTP)
			.in(SecurityScheme.In.HEADER)
			.scheme("bearer")
			.bearerFormat("JWT")
			.name("security");
	}

}
