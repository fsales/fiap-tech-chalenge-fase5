package br.com.wells.app.infrastructure.config.spring.config.app;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "app")
public class WellsUsuarioAppProperties {

	private Api api;

	public record Api(Security security) {

	}

	@Data
	public static class Security {

		@NestedConfigurationProperty
		private Token token;

		public record Token(String secret) {

		}

	}

}
