package br.com.wells.app.wellsproduto.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    @NotBlank(message = "")
    private String nome;

    @NotNull(message = "")
    private int qtdEstoque;

    @NotNull(message = "")
    private double valorVenda;

    @NotBlank(message = "")
    private String descricao;

    @NotBlank(message = "")
    private String foto;

}
