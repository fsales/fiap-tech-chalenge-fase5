package br.com.wells.wellsgateway.infrastructure.spring.cloud.gateway.filter;

import br.com.wells.wellsgateway.infrastructure.spring.cloud.gateway.filter.exception.MissingAuthorizationHeaderException;
import br.com.wells.wellsgateway.infrastructure.spring.cloud.gateway.filter.exception.TokenValidationException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	private static final String BEARER = "Bearer ";

	private static final String ERROR_VALIDATING_TOKEN = "Error occurred while validating token: ";

	private final RouteValidator validator;

	private final WebClient.Builder webClientBuilder;

	private final String validationUrl;

	public AuthenticationFilter(RouteValidator validator, WebClient.Builder webClientBuilder,
			@Value("${app.authentication.validation-url}") String validationUrl) {
		super(Config.class);
		this.validator = validator;
		this.webClientBuilder = webClientBuilder;
		this.validationUrl = validationUrl;
	}

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {
			if (validator.isSecured.test(exchange.getRequest())) {
				String authHeader = getAuthHeader(exchange.getRequest());
				return webClientBuilder.build()
					.get()
					.uri(validationUrl + authHeader)
					.retrieve()
					.bodyToMono(String.class)
					.filter(this::checkValidResponse)
					.switchIfEmpty(Mono.error(new TokenValidationException("Token validation failed.")))
					.then(chain.filter(exchange))
					.onErrorResume(throwable -> Mono
						.error(new TokenValidationException(ERROR_VALIDATING_TOKEN + throwable.getMessage())));
			}
			return chain.filter(exchange);
		});
	}

	private String getAuthHeader(ServerHttpRequest request) {
		if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
			throw new MissingAuthorizationHeaderException("Missing Authorization Header");
		}

		String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
		if (authHeader != null && authHeader.startsWith(BEARER)) {
			authHeader = authHeader.substring(BEARER.length());
		}
		return authHeader;
	}

	private boolean checkValidResponse(String response) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(response);
			JsonNode dataNode = rootNode.path("data");
			JsonNode validNode = dataNode.path("valid");
			return validNode.isBoolean() && validNode.asBoolean();
		}
		catch (Exception e) {
			return false;
		}
	}

	@Setter
	@Getter
	public static class Config {

		private boolean validResponse;

	}

}