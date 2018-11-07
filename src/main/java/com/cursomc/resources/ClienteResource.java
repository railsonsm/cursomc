package com.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursomc.models.Cliente;

@RestController
@RequestMapping("/clientes")
public class ClienteResource extends AbastractRestConttroller<Cliente, Long>{
	@Autowired
	public ClienteResource(CrudRepository<Cliente, Long> repository) {
		super(repository);
	}
	
}
