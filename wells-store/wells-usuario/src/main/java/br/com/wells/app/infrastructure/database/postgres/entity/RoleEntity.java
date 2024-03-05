package br.com.wells.app.infrastructure.database.postgres.entity;

import br.com.wells.app.infrastructure.database.postgres.entity.listener.RoleEntityListener;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "ROLE", schema = "WELLS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(of = {"id"})
@ToString
@EntityListeners({AuditingEntityListener.class, RoleEntityListener.class})
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = true, length = 100, unique = true)
    private String nome;

}
