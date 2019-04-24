package com.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cursomc.dto.CategoriaDTO;
import com.cursomc.dto.ProdutoDTO;
import com.cursomc.models.Categoria;
import com.cursomc.models.Produto;
import com.cursomc.resources.utils.URL;
import com.cursomc.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {
	@Autowired
	private ProdutoService pedidoService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id){
		Produto pedido = pedidoService.find(id);
		return ResponseEntity.ok().body(pedido);
	}
	
	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="nome", defaultValue="") String nome,
			@RequestParam(value="categorias", defaultValue="") String categorias,
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="size", defaultValue="4") Integer size,
			@RequestParam(value="orderby", defaultValue="nome") String orderby,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Produto> list = pedidoService.search(URL.decoceParam(nome), URL.decodeIntList(categorias), page, size, orderby, direction);
		Page<ProdutoDTO> listDTO = list.map(obj->new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
	
}
