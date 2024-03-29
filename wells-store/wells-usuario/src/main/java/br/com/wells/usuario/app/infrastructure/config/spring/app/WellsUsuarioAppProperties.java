package br.com.wells.usuario.app.infrastructure.config.spring.app;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "app")
public class WellsUsuarioAppProperties {

	private String name;

	@NestedConfigurationProperty
	private Api api;

	public record Api(Security security) {

	}

	@Data
	public static class Security {

		@NestedConfigurationProperty
		private Jwt jwt;

		public record Jwt(String withIssuer) {

		}

	}

}
