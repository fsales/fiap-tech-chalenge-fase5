package br.com.wells.wellsgateway.infrastructure.spring.config.app;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "app")
@Data
public class AppConfig {

	@NestedConfigurationProperty
	private Endpoint endpoint;

	public record Endpoint(List<String> openApiEndpoints) {

	}

}
