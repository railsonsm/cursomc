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
	
	public Cliente find(Long id) {
		Optional<Cliente> cliente = cRepository.findById(id);
		return cliente.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id));
	}
}
