package br.com.wells.app.infrastructure.config.spring.config.security;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import br.com.wells.app.infrastructure.config.spring.security.SecurityFilter;
import br.com.wells.app.infrastructure.config.spring.security.jwt.JWTToken;
import br.com.wells.app.infrastructure.config.spring.security.jwt.JwtAuthenticationEntryPoint;
import br.com.wells.app.infrastructure.config.spring.security.user.UsuarioCustomDetailsService;
import br.com.wells.app.infrastructure.config.spring.security.user.impl.UsuarioCustomDetailsServiceImpl;
import br.com.wells.core.domain.usuario.usecases.ConsultarUsuarioPorUsernameUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfigurations {

	private static final String[] DOCUMENTATION_OPENAPI = { "/docs/index.html", "/docs-wells-usuario.html",
			"/docs-wells-usuario/**", "/v3/api-docs/**", "/swagger-ui-custom.html", "/swagger-ui.html",
			"/swagger-ui/**", "/**.html", "/webjars/**", "/configuration/**", "/swagger-resources/**" };

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, SecurityFilter securityFilter)
			throws Exception {
		return httpSecurity.csrf(AbstractHttpConfigurer::disable)
			.formLogin(AbstractHttpConfigurer::disable)
			.httpBasic(AbstractHttpConfigurer::disable)
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(authorize -> authorize

				.requestMatchers(HttpMethod.POST, "/api/v1/usuarios")
				.permitAll()
				.requestMatchers(HttpMethod.POST, "/api/v1/auth/login", "/api/v1/auth/token")
				.permitAll()
				.requestMatchers(HttpMethod.GET, "/api/v1/auth/validate")
				.permitAll()
				.requestMatchers(DOCUMENTATION_OPENAPI)
				.permitAll()
				.anyRequest()
				.authenticated())
			.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
			.exceptionHandling(ex -> ex.authenticationEntryPoint(new JwtAuthenticationEntryPoint()))
			.build();
	}

	@Bean
	public SecurityFilter securityFilter(JWTToken JWTToken, UsuarioCustomDetailsService usuarioCustomDetailsService) {
		return new SecurityFilter(JWTToken, usuarioCustomDetailsService);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService(
			ConsultarUsuarioPorUsernameUseCase consultarUsuarioPorUsernameUseCase) {
		return new UsuarioCustomDetailsServiceImpl(consultarUsuarioPorUsernameUseCase);
	}

	@Bean
	public AuthenticationProvider authenticationProvider(
			ConsultarUsuarioPorUsernameUseCase consultarUsuarioPorUsernameUseCase) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService(consultarUsuarioPorUsernameUseCase));
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

}
