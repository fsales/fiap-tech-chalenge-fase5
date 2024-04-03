package br.com.wells.app.wellsproduto.mapper;

import br.com.wells.app.wellsproduto.model.Produto;
import br.com.wells.app.wellsproduto.model.dto.ProdutoDTO;

public class ProdutoMapper {

    public static Produto convertToProduto(ProdutoDTO produtoDTO) {

    return new Produto(produtoDTO.getNome(), produtoDTO.getQtdEstoque(), produtoDTO.getValorVenda(), produtoDTO.getDescricao(), produtoDTO.getFoto());
    }
}
