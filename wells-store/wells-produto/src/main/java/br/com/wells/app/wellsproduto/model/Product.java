package br.com.wells.app.wellsproduto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRODUTO")
@Entity
@Setter
public class Product {

	private static final long serialVersionUID = -3766858690685689916L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, unique = true, nullable = false)
	private Long id;

	@Column(name = "NOME", nullable = false)
	private String nome;

	@Column(name = "QTD", nullable = false)
	private int qtdEstoque = 0;

	@Column(name = "VALOR", nullable = false)
	private double valorVenda;

	@Column(name = "DESCRICAO", length = 2000)
	private String descricao;

	// @Lob
	// @Column(name = "FOTO", columnDefinition = "MEDIUMLOB", length = 2000)
	@Column(name = "FOTO", length = 2000)
	private String foto;

}
