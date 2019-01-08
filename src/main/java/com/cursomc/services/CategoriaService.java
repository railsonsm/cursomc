package com.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.models.Categoria;
import com.cursomc.repositories.CategoriaRepository;
import com.cursomc.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository cRepository;
	
	public Categoria buscar(Long id) {
		Optional<Categoria> categoria = cRepository.findById(id);
		return categoria.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id));
	}

	public Categoria insert(Categoria categoria) {
		return cRepository.save(categoria);
	}
}
