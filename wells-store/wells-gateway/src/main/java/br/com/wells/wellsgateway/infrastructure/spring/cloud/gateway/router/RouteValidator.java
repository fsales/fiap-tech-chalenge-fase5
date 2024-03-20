package br.com.wells.wellsgateway.infrastructure.spring.cloud.gateway.router;

import java.util.List;
import java.util.function.Predicate;

import br.com.wells.wellsgateway.infrastructure.spring.config.app.WellsGatewayProperties;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {

	private List<WellsGatewayProperties.Endpoint> openApiEndpoints;

	public Predicate<ServerHttpRequest> isSecured = request -> openApiEndpoints.stream()
		.noneMatch(endpoint -> request.getURI().getPath().contains(endpoint.path()) && (endpoint.methods() == null
				|| endpoint.methods().isEmpty() || endpoint.methods().contains(request.getMethod().toString())));

	public RouteValidator(WellsGatewayProperties wellsGatewayProperties) {
		this.openApiEndpoints = wellsGatewayProperties.getOpenApiEndpoints();
	}

}
