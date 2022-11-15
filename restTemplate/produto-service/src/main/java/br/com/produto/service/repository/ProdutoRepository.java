package br.com.produto.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.produto.service.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	Optional<Produto> findByNome(String nome);

}
