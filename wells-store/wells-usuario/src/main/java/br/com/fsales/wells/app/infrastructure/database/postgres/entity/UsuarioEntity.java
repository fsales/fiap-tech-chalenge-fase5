package br.com.fsales.wells.app.infrastructure.database.postgres.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "USUARIO", schema = "WELLS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(of = {"id"})
@ToString
@EntityListeners(AuditingEntityListener.class)
public class UsuarioEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME", nullable = true, length = 100)
    private String username;

    @Column(name = "SENHA", nullable = true, length = 200)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 25)
    @Builder.Default
    private Role role = Role.ROLE_CLIENTE;

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

    public enum Role {
        ROLE_ADMIN, ROLE_CLIENTE
    }
}
