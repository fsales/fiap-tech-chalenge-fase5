package br.com.wells.wellspagamento.infrastructure.spring.config.app;

import java.net.URI;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.util.UriComponentsBuilder;

public final class ApiRoutes {

	// URI base da API
	public static final String API = "/api";

	// Versão da API
	public static final String VERSAO = "/v1";

	public static final String ACTUATOR = "/actuator";

	private ApiRoutes() {
	}

	private static URI construirUriPorId(String recurso, Long id) {
		return construirUriPorId(recurso, Map.of("id", id));
	}

	private static URI construirUriPorId(String recurso, Map<String, Long> uriVariables) {
		String path = uriVariables.keySet().stream().map(key -> "/{" + key + "}").collect(Collectors.joining());

		return UriComponentsBuilder.fromPath(API + VERSAO + "/" + recurso)
			.path(path)
			.buildAndExpand(uriVariables)
			.toUri();
	}

}
