package com.cursomc.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cursomc.dto.ClienteDTO;
import com.cursomc.dto.ClienteNewDTO;
import com.cursomc.models.Cidade;
import com.cursomc.models.Cliente;
import com.cursomc.models.Endereco;
import com.cursomc.models.enums.TipoCliente;
import com.cursomc.repositories.ClienteRepository;
import com.cursomc.repositories.EnderecoRepository;
import com.cursomc.security.UserSS;
import com.cursomc.services.exception.AuthorizationException;
import com.cursomc.services.exception.DataIntegretyException;
import com.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository cRepository;
	@Autowired
	private EnderecoRepository eRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private S3Service s3Service;
	@Autowired
	private ImageService imageService;
	
	@Value("${img.prefix.client.profile}")
	private String prefix;
	@Value("${img.profile.size}")
	private Integer size;

	public Cliente find(Integer id) {
		Optional<Cliente> cliente = cRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	public Cliente update(Cliente obj) {
		Cliente cliente = find(obj.getId());
		updateData(cliente, obj);
		return cRepository.save(cliente);
	}

	@Transactional
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		cliente = cRepository.save(cliente);
		eRepository.saveAll(cliente.getEnderecos());
		return cliente;
	}

	public void delete(Integer id) {
		try {
			cRepository.delete(find(id));
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegretyException("Não é possível excluir um cliente que possui pedidos");
		}

	}

	public List<Cliente> findAll() {
		return cRepository.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer size, String orderby, String direction) {
		PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), orderby);
		return cRepository.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null, null);
	}

	public Cliente fromDTO(ClienteNewDTO dto) {
		Cliente cliente = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpfOuCnpj(), TipoCliente.toUnum(dto.getTipo()),
				encoder.encode(dto.getSenha()));
		Cidade cidade = new Cidade(dto.getCidadeId());
		Endereco endereco = new Endereco(null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(), dto.getBairro(), dto.getCep(), cliente, cidade);
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(dto.getTelefone1());
		if (Objects.nonNull(dto.getTelefone2()))
			cliente.getTelefones().add(dto.getTelefone2());
		if (Objects.nonNull(dto.getTelefone3()))
			cliente.getTelefones().add(dto.getTelefone3());
		return cliente;
	}

	private void updateData(Cliente cliente, Cliente obj) {
		cliente.setNome(obj.getNome());
		cliente.setEmail(obj.getEmail());
	}

	public URI uploadProfilePicture(MultipartFile multipartFile) {
		UserSS user = UserService.authenticated();
		if (Objects.isNull(user))
			throw new AuthorizationException("Acesso negado");
		
		BufferedImage bufferedImage  = imageService.getJpgImageFromFile(multipartFile);
		bufferedImage = imageService.cropSquare(bufferedImage);
		bufferedImage = imageService.resize(bufferedImage, size);
		
		String filename = prefix + user.getId() + ".jpg";
		return s3Service.uploadFile(imageService.getInputStream(bufferedImage, "jpg"), filename, "image");
	}
}
