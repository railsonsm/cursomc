package com.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cursomc.models.Categoria;
import com.cursomc.repositories.CategoriaRepository;
import com.cursomc.services.exception.DataIntegretyException;
import com.cursomc.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository cRepository;

	public Categoria find(Long id) {
		Optional<Categoria> categoria = cRepository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	public Categoria insert(Categoria categoria) {
		return cRepository.save(categoria);
	}

	public Categoria update(Categoria categoria) {
		find(categoria.getId());
		return cRepository.save(categoria);
	}

	public void delete(Long id) {
		try {
			cRepository.delete(find(id));
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegretyException("Não é possível excluir uma categoria que possui produtos");
		}

	}

	public List<Categoria> findAll() {
		return cRepository.findAll();
	}
}
