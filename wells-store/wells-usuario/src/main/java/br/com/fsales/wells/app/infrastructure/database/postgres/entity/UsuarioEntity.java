package br.com.fsales.wells.app.infrastructure.database.postgres.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "USUARIO", nullable = true, length = 100)
    private String usuario;

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
        ROLE_ADMIN, ROLE_CLIENTE;
    }

}
