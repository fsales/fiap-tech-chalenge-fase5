package br.com.wells.app.infrastructure.spring.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UsuarioCustomDetails implements UserDetails {

    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public UsuarioCustomDetails(
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    // Método para obter um Map de roles
    public Map<String, String> getRolesMap() {
        Map<String, String> rolesMap = new HashMap<>();
        for (GrantedAuthority authority : authorities) {
            rolesMap.put(getRolesMapKey(), authority.getAuthority().substring("ROLE_".length()));
        }
        return Collections.unmodifiableMap(rolesMap);
    }

    // Método para obter a chave utilizada no método getRolesMap()
    public String getRolesMapKey() {
        return "role";
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
