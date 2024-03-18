package br.com.wells.wellsgateway.infrastructure.spring.cloud.gateway.filter;

import java.util.List;
import java.util.function.Predicate;

import br.com.wells.wellsgateway.infrastructure.spring.config.app.WellsGatewayProperties;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {

	public final WellsGatewayProperties wellsGatewayProperties;

	private List<String> openApiEndpoints;

	public Predicate<ServerHttpRequest> isSecured = request -> openApiEndpoints.stream()
		.noneMatch(uri -> request.getURI().getPath().contains(uri));

	public RouteValidator(WellsGatewayProperties wellsGatewayProperties) {
		this.wellsGatewayProperties = wellsGatewayProperties;
		this.openApiEndpoints = wellsGatewayProperties.getEndpoint().openApiEndpoints();
	}

}
