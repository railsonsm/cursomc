package com.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.models.Cliente;
import com.cursomc.repositories.ClienteRepository;
import com.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository cRepository;
	
	public Cliente buscar(Long id) {
		Optional<Cliente> categoria = cRepository.findById(id);
		return categoria.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id));
	}
}
