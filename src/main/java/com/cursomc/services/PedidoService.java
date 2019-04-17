package com.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.models.Cliente;
import com.cursomc.models.Pedido;
import com.cursomc.repositories.ClienteRepository;
import com.cursomc.repositories.PedidoRepository;
import com.cursomc.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido find(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id));
	}
}
