package br.com.wells.usuario.app.infrastructure.config.spring.app;

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

	public static final String USUARIOS_URI = API + VERSAO + "/usuarios";

	public static final String AUTH_URI = API + VERSAO + "/auth";

	private ApiRoutes() {
	}

	public static URI construirUriUsuarioPorId(Long id) {
		return construirUriPorId(USUARIOS_URI, id);
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
