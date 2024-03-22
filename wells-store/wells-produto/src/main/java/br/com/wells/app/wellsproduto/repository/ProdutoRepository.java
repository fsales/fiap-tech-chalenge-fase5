package br.com.wells.app.wellsproduto.repository;


import br.com.wells.app.wellsproduto.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    //listar e pesquisar com paginação
//    @Query("SELECT u FROM Produto u WHERE u.nome like %?1%")
//    public List<Produto> search(String keyword);
//
//    @Query("SELECT u FROM Produto u WHERE u.nome like %?1%")
//    public Page<Produto> search(String keyword, Pageable page);

    public Page<Produto> findAll(Pageable page);
}