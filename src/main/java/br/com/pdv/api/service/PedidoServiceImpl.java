package br.com.pdv.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pdv.api.exception.ObjectNotFoundException;
import br.com.pdv.api.model.domain.Pedido;
import br.com.pdv.api.model.repository.PedidoRepository;

@Service
public class PedidoServiceImpl {

	@Autowired
	private PedidoRepository repo;

	public Pedido findById(Integer id) {
		Optional<Pedido> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	public void delete(Pedido entity) {
		repo.delete(entity);
	}

	public List<Pedido> findAll() {
		return repo.findAll();
	}

}