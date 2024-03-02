package br.com.wells.app.infrastructure.spring.config.security;


import br.com.wells.app.usecases.security.TokenUserCase;
import br.com.wells.app.usecases.security.UsuarioCustomDetailsUserCase;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class SecurityFilter extends  OncePerRequestFilter {

    @Autowired
    private TokenUserCase tokenUserCase;

    @Autowired
    private UsuarioCustomDetailsUserCase usuarioCustomDetailsUserCase;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if (token != null) {
            var login = tokenUserCase.validateToken(
                    token
            );
            UserDetails user = usuarioCustomDetailsUserCase.loadUserByUsername(
                    login
            );

            var authentication = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    user.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(
            HttpServletRequest request
    ) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}