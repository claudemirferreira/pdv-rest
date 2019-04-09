package br.com.pdv.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pdv.api.exception.ObjectNotFoundException;
import br.com.pdv.api.model.domain.Cliente;
import br.com.pdv.api.model.repository.ClienteRepository;

@Service
public class ClienteServiceImpl {

	@Autowired
	private ClienteRepository repo;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public void delete(Cliente entity) {
		repo.delete(entity);
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

}