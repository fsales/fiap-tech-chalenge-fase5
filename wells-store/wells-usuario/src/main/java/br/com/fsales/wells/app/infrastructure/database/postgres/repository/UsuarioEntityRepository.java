package br.com.fsales.wells.app.infrastructure.database.postgres.repository;

import java.util.Optional;

import br.com.fsales.wells.app.infrastructure.database.postgres.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioEntityRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByUsuario(String username);

    @Query("select u.role from UsuarioEntity u where u.usuario like :usuario")
    UsuarioEntity.Role findRoleByUsuario(String usuario);
}
