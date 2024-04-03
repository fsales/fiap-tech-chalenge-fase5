package br.com.wells.app.wellsproduto.controller;

import br.com.wells.app.wellsproduto.Service.ProdutoService;
import br.com.wells.app.wellsproduto.model.dto.ProdutoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/api/v1/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity saveProduto(@Valid @RequestBody ProdutoDTO produtoDTO) {

        return new ResponseEntity<>(produtoService.createProduto(produtoDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getAllItens() {
        return ResponseEntity.ok().body(produtoService.getAllItens());
    }

    @GetMapping("/{id}")
    public ResponseEntity getProdutoById(@Valid @PathVariable(name = "id") @NotNull Long id) {
        return ResponseEntity.ok().body(produtoService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity editProduto(@Valid @PathVariable(name = "id") @NotNull Long id,
                                   @Valid @RequestBody ProdutoDTO produtoDTO) throws Exception {

        return new ResponseEntity<>(produtoService.editProduto(id, produtoDTO), HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduto(@Valid @PathVariable(name = "id") @NotNull Long id) {
        return produtoService.deleteProduto(id);
    }





}
