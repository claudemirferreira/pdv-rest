package br.com.pdv.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pdv.api.exception.ObjectNotFoundException;
import br.com.pdv.api.model.domain.Categoria;
import br.com.pdv.api.model.domain.Cidade;
import br.com.pdv.api.model.repository.CidadeRepository;

@Service
public class CidadeServiceImpl {

	@Autowired
	private CidadeRepository repo;

	public Cidade findById(Integer id) {
		Optional<Cidade> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public void delete(Cidade entity) {
		repo.delete(entity);
	}

	public List<Cidade> findAll() {
		return repo.findAll();
	}

}