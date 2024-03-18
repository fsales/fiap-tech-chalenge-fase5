package br.com.wells.wellsgateway.infrastructure.spring.cloud.gateway.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component

public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	private final RouteValidator validator;

	public AuthenticationFilter(RouteValidator validator) {
		super(Config.class);
		this.validator = validator;
	}

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {
			return chain.filter(exchange);
		});
	}

	public static class Config {

	}

}
