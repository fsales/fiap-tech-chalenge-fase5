package br.com.wells.usuario.app.infrastructure.database.postgres.repository;

import br.com.wells.usuario.app.infrastructure.database.postgres.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long> {

	@Query("SELECT r FROM RoleEntity r WHERE UPPER(r.nome) IN :nomes")
	Optional<Set<RoleEntity>> findByNomesIgnoreCase(Set<String> nomes);

	default Optional<Set<RoleEntity>> findByNomesIgnoreCaseSafe(Set<String> nomes) {
		return Optional.ofNullable(nomes).filter(set -> !set.isEmpty()).flatMap(this::findByNomesIgnoreCase);
	}

}
