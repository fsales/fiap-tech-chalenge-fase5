package br.com.wells.app.wellsproduto.Service.impl;

import br.com.wells.app.wellsproduto.Service.ProdutoService;
import br.com.wells.app.wellsproduto.mapper.ProdutoMapper;
import br.com.wells.app.wellsproduto.model.Produto;
import br.com.wells.app.wellsproduto.model.dto.ProdutoDTO;
import br.com.wells.app.wellsproduto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Produto createProduto(ProdutoDTO produtoDTO) {

        Produto produto = ProdutoMapper.convertToProduto(produtoDTO);
        return produtoRepository.save(produto);
    }

    @Override
    public List<Produto> getAllItens() {
        return produtoRepository.findAll();
    }

    @Override
    public Produto findById(Long id) {
        return produtoRepository.findById(id).get();
    }

    @Override
    public ResponseEntity deleteProduto(Long id) {
        if (produtoRepository.findById(id).isPresent()) {
            produtoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Número de Id inválido");
    }

    @Override
    public Produto editProduto(Long id, ProdutoDTO produtoDTO) throws Exception {
        Optional<Produto> ProdutoOptional = produtoRepository.findById(id);
        if (ProdutoOptional.isPresent()) {
            Produto produto = ProdutoMapper.convertToProduto(produtoDTO);
            produto.setId(id);
            return produtoRepository.save(produto);
        }
        else {
            throw new Exception("Número de id inválido");
        }
    }

}
