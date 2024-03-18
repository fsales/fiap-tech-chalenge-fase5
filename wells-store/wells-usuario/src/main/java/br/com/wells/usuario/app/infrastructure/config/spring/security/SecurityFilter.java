package br.com.wells.usuario.app.infrastructure.config.spring.security;

import br.com.wells.usuario.app.infrastructure.config.spring.security.jwt.JWTToken;
import br.com.wells.usuario.app.infrastructure.config.spring.security.user.UsuarioCustomDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	private final JWTToken tokenUserCase;

	private final UsuarioCustomDetailsService usuarioCustomDetailsService;

	public SecurityFilter(JWTToken JWTToken, UsuarioCustomDetailsService usuarioCustomDetailsService) {
		this.tokenUserCase = JWTToken;
		this.usuarioCustomDetailsService = usuarioCustomDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		var token = this.recoverToken(request);
		if (token != null) {
			var login = tokenUserCase.validateToken(token);
			UserDetails user = usuarioCustomDetailsService.loadUserByUsername(login);

			var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);
	}

	private String recoverToken(HttpServletRequest request) {
		var authHeader = request.getHeader("Authorization");
		if (authHeader == null)
			return null;
		return authHeader.replace("Bearer ", "");
	}

}