package br.com.wells.wellsgateway.infrastructure.spring.cloud.gateway.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${authentication.validation-url}")
	private String validationUrl;

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

				String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
				if (authHeader != null && authHeader.startsWith(BEARER)) {
					authHeader = authHeader.substring(BEARER.length());
				}

				return webClientBuilder.build()
					.get()
					.uri(validationUrl + authHeader)
					.retrieve()
					.bodyToMono(String.class)
					.flatMap(response -> chain.filter(exchange))
					.onErrorResume(throwable -> Mono.error(
							new RuntimeException("Error occurred while validating token: " + throwable.getMessage())));
			}

			return chain.filter(exchange);
		});
	}

	public static class Config {

	}

}
