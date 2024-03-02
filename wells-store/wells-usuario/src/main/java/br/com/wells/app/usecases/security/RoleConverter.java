package br.com.wells.app.usecases.security;

import br.com.wells.core.domain.usuario.model.Role;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public final class RoleConverter {

    private RoleConverter(){}

    /**
     * @param roles
     * @return
     */
    public static Collection<GrantedAuthority> convertToAuthorities(
            List<Role> roles
    ) {
        return roles.stream()
                .map(role -> (GrantedAuthority) role::name)
                .toList();
    }
}
