package com.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cursomc.dto.CategoriaDTO;
import com.cursomc.models.Categoria;
import com.cursomc.models.Cliente;
import com.cursomc.repositories.CategoriaRepository;
import com.cursomc.services.exception.DataIntegretyException;
import com.cursomc.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository cRepository;

	public Categoria find(Integer id) {
		Optional<Categoria> categoria = cRepository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}
	
	public Categoria update(Categoria obj) {
		Categoria categoria = find(obj.getId());
		updateData(categoria, obj);
		return cRepository.save(categoria);
	}

	public Categoria insert(Categoria categoria) {
		return cRepository.save(categoria);
	}



	public void delete(Integer id) {
		try {
			cRepository.delete(find(id));
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegretyException("Não é possível excluir uma categoria que possui produtos");
		}

	}

	public List<Categoria> findAll() {
		return cRepository.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer size, String orderby, String direction){
		PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), orderby);
		return cRepository.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO dto) {
		return new Categoria(dto.getId(), dto.getNome());
	}
	
	private void updateData(Categoria categoria, Categoria obj) {
		categoria.setNome(obj.getNome());
	}
}
