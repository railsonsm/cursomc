package com.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cursomc.models.Categoria;
import com.cursomc.models.Cliente;
import com.cursomc.models.Produto;
import com.cursomc.models.Produto;
import com.cursomc.repositories.CategoriaRepository;
import com.cursomc.repositories.ClienteRepository;
import com.cursomc.repositories.ProdutoRepository;
import com.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository pedidoRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Produto find(Integer id) {
		Optional<Produto> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	public Page<Produto> search(String nome, List<Integer> ids,Integer page, Integer size, String orderby, String direction) {
		PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), orderby);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return pedidoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
}
