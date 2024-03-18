package br.com.wells.wellsgateway.infrastructure.spring.cloud.gateway.filter;

import java.util.Objects;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component

public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	private static final String BEARER = "Bearer ";

	private final RouteValidator validator;

	public AuthenticationFilter(RouteValidator validator) {
		super(Config.class);
		this.validator = validator;
	}

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {

			if (validator.isSecured.test(exchange.getRequest())) {
				if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new RuntimeException("Missing Authorization Header");
				}

				String authHeader = Objects
					.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION))
					.get(0);
				if (authHeader != null && authHeader.startsWith(BEARER)) {
					authHeader = authHeader.substring(BEARER.length());
				}

			}

			return chain.filter(exchange);
		});
	}

	public static class Config {

	}

}
