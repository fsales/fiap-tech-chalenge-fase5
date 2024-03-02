package br.com.wells.app.usecases.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioCustomDetailsUserCase {
    UserDetails loadUserByUsername(
            String username
    );
}
