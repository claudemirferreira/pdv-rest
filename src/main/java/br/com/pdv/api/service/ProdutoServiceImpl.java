package br.com.pdv.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.pdv.api.dto.ProdutoDTO;
import br.com.pdv.api.exception.DataIntegrityException;
import br.com.pdv.api.exception.ObjectNotFoundException;
import br.com.pdv.api.model.domain.Categoria;
import br.com.pdv.api.model.domain.Produto;
import br.com.pdv.api.model.repository.CategoriaRepository;
import br.com.pdv.api.model.repository.ProdutoRepository;

@Service
public class ProdutoServiceImpl {

	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Produto findById(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}

	public Produto save(Produto entity) {
		repo.save(entity);
		return entity;
	}

	public Produto update(Produto obj) {
		Produto newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updateData(Produto newObj, Produto obj) {
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

	public List<ProdutoDTO> findAll() {
		return repo.findAll().stream().map(obj -> new ProdutoDTO(obj)).collect(Collectors.toList());
	}

	public Page<Produto> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);	
	}

}