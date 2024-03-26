package br.com.wells.wellsgateway.infrastructure.swagger.config;

import java.util.ArrayList;
import java.util.List;

import br.com.wells.wellsgateway.infrastructure.swagger.properties.SwaggerConfigProperties;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.EnableWebFlux;

@Configuration
@EnableWebFlux
public class SpringDocOpenApiConfig {

	final RouteDefinitionLocator locator;

	public SpringDocOpenApiConfig(RouteDefinitionLocator locator) {
		this.locator = locator;
	}

	@Bean
	public OpenAPI openAPI(SwaggerConfigProperties swaggerConfigProperties) {
		var application = swaggerConfigProperties.getApplication();

		return new OpenAPI().info(new Info().title(application.title())
			.description(application.description())
			.version(application.version())
			.license(new License().name("MIT License")));
	}

	// @Bean
	// public CorsWebFilter corsWebFilter() {
	// CorsConfiguration corsConfig = new CorsConfiguration();
	// // corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
	// corsConfig.setAllowedOrigins(corsConfig.getAllowedOrigins());
	// corsConfig.setMaxAge(3600L);
	// corsConfig.addAllowedMethod("*");
	// corsConfig.addAllowedHeader("Requestor-Type");
	// corsConfig.addExposedHeader("X-Get-Header");
	//
	// UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	// source.registerCorsConfiguration("/**", corsConfig);
	//
	// return new CorsWebFilter(source);
	// }

//	@Bean
//	public List<GroupedOpenApi> apis() {
//		List<GroupedOpenApi> groups = new ArrayList<>();
//		List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
//		assert definitions != null;
//		definitions.stream()
//			.filter(routeDefinition -> routeDefinition.getId().matches(".*-service"))
//			.forEach(routeDefinition -> {
//				String name = routeDefinition.getId().replaceAll("-service", "");
//				groups.add(GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build());
//			});
//		return groups;
//	}

}
