package br.com.wells.app.wellsproduto.Service;

import br.com.wells.app.wellsproduto.model.Produto;
import br.com.wells.app.wellsproduto.model.dto.ProdutoDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProdutoService {

    Produto createProduto(ProdutoDTO itemDTO);

    List<Produto> getAllItens();

    Produto findById(Long id);

    ResponseEntity deleteProduto(Long id);

    Produto editProduto(Long id, ProdutoDTO clientDTO) throws Exception;

}
