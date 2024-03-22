package br.com.wells.app.wellsproduto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebConfigSecurity {

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
}
