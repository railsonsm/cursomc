package com.cursomc.resources;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

public abstract class AbastractRestConttroller<T, ID extends Serializable> {
	private CrudRepository<T, ID> repo;
	
	public AbastractRestConttroller(CrudRepository<T, ID> repository) {
		this.repo = repository;
	}
	
	@GetMapping("/{id}")
	public @ResponseBody Optional<T> find(@PathVariable ID id){
		Optional<T> result = this.repo.findById(id);
		return result;
	}
	
}
