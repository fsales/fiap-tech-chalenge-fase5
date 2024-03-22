package br.com.wells.wellspagamento.infrastructure.database.postgres.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.wells.wellspagamento.infrastructure.database.postgres.entity.enumeration.StatusPagamentoEnum;
import br.com.wells.wellspagamento.infrastructure.database.postgres.entity.enumeration.TipoCartaoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Builder(toBuilder = true)
@Getter
@Setter
@Entity
@Table(name = "pagamento", schema = "wells")
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

    @Positive
	@NotNull
	@Column(name = "valor", nullable = false, precision = 19, scale = 2)
	private BigDecimal valor;

	@Size(max = 255)
	@NotNull
	@Column(name = "nome", nullable = false)
	private String nome;

	@Size(max = 19)
	@NotNull
	@Column(name = "numero", nullable = false, length = 19)
	private String numero;

	@NotNull
	@Column(name = "expiracao", nullable = false)
	private LocalDate expiracao;

	@Size(max = 3)
	@NotNull
	@Column(name = "codigo", nullable = false, length = 3)
	private String codigo;

	@Size(max = 10)
	@NotNull
	@Column(name = "status", nullable = false, length = 10)
	private StatusPagamentoEnum status;

	@Size(max = 26)
	@NotNull
	@Column(name = "tipo_cartao", nullable = false, length = 26)
	private TipoCartaoEnum tipoCartao;

	@NotNull
	@Column(name = "pedido_id", nullable = false)
	private Long pedidoId;

    @CreatedDate
	@NotNull
	@Column(name = "data_criacao", nullable = false)
	private LocalDateTime dataCriacao;

    @LastModifiedDate
	@Column(name = "data_modificacao")
	private LocalDateTime dataModificacao;

    @CreatedBy
	@Size(max = 255)
	@NotNull
	@Column(name = "criado_por", nullable = false)
	private String criadoPor;

    @LastModifiedBy
	@Size(max = 255)
	@Column(name = "modificado_por")
	private String modificadoPor;

}