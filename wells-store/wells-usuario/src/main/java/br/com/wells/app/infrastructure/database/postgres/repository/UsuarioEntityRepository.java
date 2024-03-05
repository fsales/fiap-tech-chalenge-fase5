package br.com.wells.app.infrastructure.database.postgres.repository;

import br.com.wells.app.infrastructure.database.postgres.entity.UsuarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioEntityRepository extends JpaRepository<UsuarioEntity, Long> {

    // Usando LEFT JOIN FETCH para incluir o relacionamento roles
    @EntityGraph(attributePaths = "roles", type = EntityGraph.EntityGraphType.LOAD)
    Optional<UsuarioEntity> findByUsername(String username);

    boolean existsByUsernameIgnoreCase(String username);

    Page<UsuarioEntity> findAll(Pageable pageable);
}
