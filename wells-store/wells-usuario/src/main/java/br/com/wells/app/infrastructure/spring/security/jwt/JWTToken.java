package br.com.wells.app.infrastructure.spring.security.jwt;

import br.com.wells.app.infrastructure.spring.security.user.UsuarioCustomDetails;

public interface JWTToken {
    /**
     * @param usuario
     * @return
     */
    String generateToken(
            UsuarioCustomDetails usuario
    );

    /**
     * @param token
     * @return
     */
    String validateToken(String token);
}
