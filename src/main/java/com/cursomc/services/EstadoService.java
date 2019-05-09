package com.cursomc.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.dto.EstadoDTO;
import com.cursomc.models.Estado;
import com.cursomc.repositories.EstadoRepository;


@Service
public class EstadoService {
	
	private EstadoRepository estadoRepository;
	
	public EstadoService (EstadoRepository estadoRepository) {
		this.estadoRepository = estadoRepository;
	}
	
	public List<EstadoDTO> findAll(){
		List<Estado> list = estadoRepository.findAllByOrderByNome();
		List<EstadoDTO> response = list.stream().map(e->new EstadoDTO(e)).collect(Collectors.toList());
		return response;
	}

}
