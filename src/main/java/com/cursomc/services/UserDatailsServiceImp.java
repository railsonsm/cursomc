package com.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cursomc.models.Cliente;
import com.cursomc.repositories.ClienteRepository;
import com.cursomc.security.UserSS;

@Service
public class UserDatailsServiceImp implements UserDetailsService{
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente cliente = clienteRepository.findByEmail(email);
		if(cliente==null)
				throw new UsernameNotFoundException(email);
		return new UserSS(cliente.getId(), cliente.getSenha(), cliente.getEmail(), cliente.getPerfis());
	}

}
