package br.com.wells.usuario.app.infrastructure.config.spring.security.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		log.info("Http Status 401 {}", authException.getMessage());
		response.setHeader("www-authenticate", "Bearer realm='/api/v1/auth'");
		response.sendError(401);
	}

}