package br.com.pdv.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.pdv.api.dto.CategoriaDTO;
import br.com.pdv.api.exception.DataIntegrityException;
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

	public Categoria update(Categoria obj) {
		Categoria newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}

	public void deleteById(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			System.out.println(e.getMessage());
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos associados");
		}
	}

	public List<CategoriaDTO> findAll() {
		return repo.findAll().stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
	}

}