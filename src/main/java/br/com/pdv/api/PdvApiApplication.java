package br.com.pdv.api;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.pdv.api.model.domain.Categoria;
import br.com.pdv.api.model.repository.CategoriaRepository;

@SpringBootApplication
public class PdvApiApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(PdvApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Categoria c1 = new Categoria(null, "Informática");
		Categoria c2 = new Categoria(null, "Escritorio");
		repo.saveAll(Arrays.asList(c1,c2));
		
	}
	
	

}