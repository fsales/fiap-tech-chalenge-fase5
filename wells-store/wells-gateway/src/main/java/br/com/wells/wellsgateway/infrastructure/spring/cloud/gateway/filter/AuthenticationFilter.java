package br.com.wells.wellsgateway.infrastructure.spring.cloud.gateway.filter;

import java.util.Objects;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component

public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	private static final String BEARER = "Bearer ";

	private final RouteValidator validator;

	private final WebClient.Builder webClientBuilder;

	public AuthenticationFilter(RouteValidator validator, WebClient.Builder webClientBuilder) {
		super(Config.class);
		this.validator = validator;
		this.webClientBuilder = webClientBuilder;
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

				return webClientBuilder.build()
					.get()
					.uri("http://wells-usuario/api/v1/auth/validate?token=" + authHeader)
					.retrieve()
					.bodyToMono(String.class)
					.flatMap(response -> {
						// Se o token for válido, passa a requisição para o serviço
						return chain.filter(exchange);
					})
					.onErrorResume(throwable -> {

						return Mono.error(new RuntimeException(
								"Error occurred while validating token: " + throwable.getMessage()));
					});

			}

			return chain.filter(exchange);
		});
	}

	public static class Config {

	}

}
