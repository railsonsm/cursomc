package com.cursomc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cursomc.models.Categoria;
import com.cursomc.models.Pedido;
import com.cursomc.models.Produto;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	
	
}
