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
public class Produto {

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

    //@Lob
    //@Column(name = "FOTO", columnDefinition = "MEDIUMLOB", length = 2000)
    @Column(name = "FOTO", length = 2000)
    private String foto;

    public Produto(String nome, int qtdEstoque, double valorVenda, String descricao, String foto) {
        this.nome = nome;
        this.qtdEstoque = qtdEstoque;
        this.valorVenda = valorVenda;
        this.descricao = descricao;
        this.foto = foto;
    }
}
