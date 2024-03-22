package br.com.wells.wellsgateway;

import br.com.wells.wellspagamento.infrastructure.database.postgres.entity.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoEntityRepository extends JpaRepository<PagamentoEntity, Long> {

}