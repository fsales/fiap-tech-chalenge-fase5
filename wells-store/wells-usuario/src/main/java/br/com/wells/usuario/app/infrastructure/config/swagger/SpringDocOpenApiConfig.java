package br.com.wells.usuario.app.infrastructure.config.swagger;

import java.util.List;

import br.com.wells.usuario.app.infrastructure.config.swagger.properties.SwaggerConfigProperties;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@OpenAPIDefinition
public class SpringDocOpenApiConfig {

	@Bean
	public OpenAPI openAPI(SwaggerConfigProperties swaggerConfigProperties) {
		var application = swaggerConfigProperties.getApplication();

		return new OpenAPI()
			// .servers(List.of(new Server().url(swaggerConfigProperties.getUrl())))
			.components(new Components().addSecuritySchemes("security", securityScheme()))
			.info(new Info().title(application.title())
				.description(application.description())
				.version(application.version())
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

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedOrigins(config.getAllowedOrigins());
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");

		source.registerCorsConfiguration("/wells-usuario/v3/api-docs", config);
		return new CorsFilter(source);
	}

}
