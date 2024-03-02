package br.com.wells.app.usecases.security;

import br.com.wells.app.infrastructure.spring.config.security.UsuarioCustomDetails;

public interface TokenUserCase {
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
