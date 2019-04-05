package br.com.pdv.api.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.pdv.api.model.domain.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> listar() {

		Categoria c1 = new Categoria(1, "Informática");
		Categoria c2 = new Categoria(1, "Escritório");

		List<Categoria> list = new ArrayList<Categoria>();
		list.add(c1);
		list.add(c2);

		return list;
	}

}