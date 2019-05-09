package com.cursomc.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.dto.CidadeDTO;
import com.cursomc.models.Cidade;
import com.cursomc.repositories.CidadeRepository;

@Service
public class CidadeService {
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public List<CidadeDTO> findByEstado(Integer estadoId){
		List<Cidade> list = cidadeRepository.findAllByEstadoIdOrderByNome(estadoId);
		List<CidadeDTO> response = list.stream().map(c->new CidadeDTO(c)).collect(Collectors.toList());
		return response;
	}
}
