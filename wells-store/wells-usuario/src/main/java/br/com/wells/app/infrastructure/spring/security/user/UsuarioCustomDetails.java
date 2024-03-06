package br.com.wells.app.infrastructure.spring.security.user;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioCustomDetails extends User implements UserDetails {

    private Long id;

    public UsuarioCustomDetails(
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities,
            Long id) {
        super(username, password, authorities);
        this.id = id;
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

    public Long id(){
        return id;
    }
}
