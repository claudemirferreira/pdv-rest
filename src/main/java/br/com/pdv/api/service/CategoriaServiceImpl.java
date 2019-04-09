package br.com.pdv.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pdv.api.exception.ObjectNotFoundException;
import br.com.pdv.api.model.domain.Categoria;
import br.com.pdv.api.model.repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl {

	@Autowired
	private CategoriaRepository repo;

	public Categoria findById(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria save(Categoria entity) {
		repo.save(entity);
		return entity;
	}

	public void delete(Categoria entity) {
		repo.delete(entity);
	}

	public List<Categoria> findAll() {
		return repo.findAll();
	}

}