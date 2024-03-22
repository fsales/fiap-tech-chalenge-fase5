package br.com.wells.usuario.app.infrastructure.config.spring.security.user;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioCustomDetails extends User implements UserDetails {

	private final Long id;

	public UsuarioCustomDetails(String username, String password, Collection<? extends GrantedAuthority> authorities,
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

	public Long id() {
		return id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UsuarioCustomDetails that = (UsuarioCustomDetails) o;
		return id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public String toString() {
		return "UsuarioCustomDetails{" + "id=" + id + ", user='" + getUsername() + '\'' + '}';
	}

}
