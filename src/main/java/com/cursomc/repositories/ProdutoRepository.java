package com.cursomc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cursomc.models.Categoria;
import com.cursomc.models.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome,java.util.List<Categoria> categorias,Pageable pageRequest);
}
