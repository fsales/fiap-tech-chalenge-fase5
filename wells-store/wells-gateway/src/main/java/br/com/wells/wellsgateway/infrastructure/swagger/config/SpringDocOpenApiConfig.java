package br.com.wells.wellsgateway.infrastructure.swagger.config;

import br.com.wells.wellsgateway.infrastructure.swagger.properties.SwaggerConfigProperties;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {

	@Bean
	public OpenAPI openAPI(SwaggerConfigProperties swaggerConfigProperties) {
		var application = swaggerConfigProperties.getApplication();

		return new OpenAPI().info(new Info().title(application.title())
			.description(application.description())
			.version(application.version())
			.license(new License().name("MIT License")));
	}

}
