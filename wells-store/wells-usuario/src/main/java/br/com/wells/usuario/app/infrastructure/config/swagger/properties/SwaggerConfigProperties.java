package br.com.wells.usuario.app.infrastructure.config.swagger.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app.swagger")
@Data
public class SwaggerConfigProperties {

	@NestedConfigurationProperty
	private Application application;

	private String url;

	public record Application(String title,

			String description,

			String version) {
	}

}