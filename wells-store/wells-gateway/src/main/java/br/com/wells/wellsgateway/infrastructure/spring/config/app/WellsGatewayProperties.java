package br.com.wells.wellsgateway.infrastructure.spring.config.app;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "app")
@Data
public class WellsGatewayProperties {

	@NestedConfigurationProperty
	private List<Endpoint> openApiEndpoints;

	public record Endpoint(String path, List<String> methods) {

	}

}
