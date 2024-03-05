package br.com.wells.app.infrastructure.database.postgres.entity.listener;

import br.com.wells.app.infrastructure.database.postgres.entity.RoleEntity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public final class RoleEntityListener {

    @PrePersist
    public void beforePersist(RoleEntity roleEntity) {
        transformarString(roleEntity);
    }

    @PreUpdate
    public void beforeUpdate(RoleEntity roleEntity) {
        transformarString(roleEntity);
    }


    public void transformarString(RoleEntity roleEntity) {
        // Converte as strings para maiúsculas e remove espaços em branco
        roleEntity.setNome(roleEntity.getNome().toUpperCase().trim());
    }
}
