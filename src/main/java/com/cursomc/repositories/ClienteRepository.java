package com.cursomc.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cursomc.models.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
	Optional<Cliente> findClienteById(Long id);
	
	Cliente findOneById(Long id);
	
	
}
