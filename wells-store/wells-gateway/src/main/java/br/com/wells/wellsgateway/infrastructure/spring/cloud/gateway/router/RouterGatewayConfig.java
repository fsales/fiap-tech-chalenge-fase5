package br.com.wells.wellsgateway.infrastructure.spring.cloud.gateway.router;

import br.com.wells.wellsgateway.infrastructure.spring.cloud.gateway.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RouterGatewayConfig {

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {

		return builder.routes()
			.route(r -> r.path("/wells-usuario/v3/api-docs").uri("lb://wells-usuario"))
			.route(r -> r.path("/api/v1/auth").uri("lb://wells-usuario"))
			.route(r -> r.path("/api/v1/usuarios").and().uri("lb://wells-usuario"))
			.build();
	}

}
