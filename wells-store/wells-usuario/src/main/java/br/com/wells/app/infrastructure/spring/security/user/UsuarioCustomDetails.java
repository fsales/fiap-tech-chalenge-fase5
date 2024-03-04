package br.com.wells.app.infrastructure.spring.security.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UsuarioCustomDetails extends User implements UserDetails {

    public UsuarioCustomDetails(
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);

    }

    // Método para obter um Map de roles
    public Map<String, String> getRolesMap() {
        Map<String, String> rolesMap = new HashMap<>();
        for (GrantedAuthority authority : getAuthorities()) {
            rolesMap.put(getRolesMapKey(), authority.getAuthority());
        }

        return rolesMap;
    }

    // Método para obter a chave utilizada no método getRolesMap()
    public String getRolesMapKey() {
        return "role";
    }

}
