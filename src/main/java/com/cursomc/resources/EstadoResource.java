package com.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursomc.dto.CidadeDTO;
import com.cursomc.dto.EstadoDTO;
import com.cursomc.services.CidadeService;
import com.cursomc.services.EstadoService;

@RequestMapping("/estados")
@RestController
public class EstadoResource {
	@Autowired
	private EstadoService estadoService;
	@Autowired
	private CidadeService cidadeService; 
	
	@RequestMapping
	public ResponseEntity<List<EstadoDTO>> findAll(){
		List<EstadoDTO> list = estadoService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping("/{estadoId}/cidades")
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId){
		List<CidadeDTO> list = cidadeService.findByEstado(estadoId);
		return ResponseEntity.ok().body(list);
	}
	
}
