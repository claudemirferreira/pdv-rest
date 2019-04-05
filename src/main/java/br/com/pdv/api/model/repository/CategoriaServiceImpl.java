package br.com.pdv.api.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pdv.api.model.domain.Categoria;

@Service
public class CategoriaServiceImpl {

	@Autowired
	private CategoriaRepository repo;

	public Categoria findById(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
	}

	public void delete(Categoria entity) {
		repo.delete(entity);
	}

	public List<Categoria> findAll() {
		return repo.findAll();
	}

}