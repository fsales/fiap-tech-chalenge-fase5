package br.com.wells.app.infrastructure.database.postgres.entity;

import br.com.wells.app.infrastructure.database.postgres.entity.listener.UsuarioEntityListener;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USUARIO", schema = "WELLS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(of = {"id"})
@ToString
@EntityListeners({AuditingEntityListener.class, UsuarioEntityListener.class})
public class UsuarioEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME", nullable = true, length = 100)
    @NotEmpty
    private String username;

    @Column(name = "SENHA", nullable = true, length = 200)
    @NotEmpty
    private String senha;


    @Builder.Default
    @ManyToMany
    @JoinTable(
            schema = "wells",
            name = "USUARIO_ROLE",
            joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<RoleEntity> roles = new HashSet<>();

    @CreatedDate
    @Column(name = "DATA_CRIACAO")
    private LocalDateTime dataCriacao;
    @LastModifiedDate
    @Column(name = "DATA_MODIFICACAO")
    private LocalDateTime dataModificacao;
    @CreatedBy
    @Column(name = "CRIADO_POR")
    private String criadoPor;
    @LastModifiedBy
    @Column(name = "MODIFICADO_POR")
    private String modificadoPor;
}
