package br.com.wells.wellsgateway.infrastructure.spring.cloud.gateway.filter;

import java.util.List;
import java.util.function.Predicate;

import br.com.wells.wellsgateway.infrastructure.spring.config.app.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {

	public final AppConfig appConfig;

	private List<String> openApiEndpoints;

	public Predicate<ServerHttpRequest> isSecured = request -> openApiEndpoints.stream()
		.noneMatch(uri -> request.getURI().getPath().contains(uri));

	public RouteValidator(AppConfig appConfig) {
		this.appConfig = appConfig;
		this.openApiEndpoints = appConfig.getEndpoint().openApiEndpoints();
	}

	// public static final List<String> openApiEndpoints = List.of(
	// "/auth/register",
	// "/auth/token",
	// "/eureka"
	// );

	// public Predicate<ServerHttpRequest> isSecured =
	// request -> openApiEndpoints
	// .stream()
	// .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
