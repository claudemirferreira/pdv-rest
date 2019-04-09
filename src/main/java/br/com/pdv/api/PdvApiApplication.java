package br.com.pdv.api;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.pdv.api.model.domain.Categoria;
import br.com.pdv.api.model.domain.Cidade;
import br.com.pdv.api.model.domain.Cliente;
import br.com.pdv.api.model.domain.Endereco;
import br.com.pdv.api.model.domain.Estado;
import br.com.pdv.api.model.domain.Produto;
import br.com.pdv.api.model.domain.enumerado.TipoCliente;
import br.com.pdv.api.model.repository.CategoriaRepository;
import br.com.pdv.api.model.repository.CidadeRepository;
import br.com.pdv.api.model.repository.ClienteRepository;
import br.com.pdv.api.model.repository.EnderecoRepository;
import br.com.pdv.api.model.repository.EstadoRepository;
import br.com.pdv.api.model.repository.ProdutoRepository;

@SpringBootApplication
public class PdvApiApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository repo;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	public static void main(String[] args) {
		SpringApplication.run(PdvApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Categoria c1 = new Categoria(null, "Informática");
		Categoria c2 = new Categoria(null, "Escritorio");

		Produto p1 = new Produto(null, "Compudator", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		c1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		c2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(c1));
		p2.getCategorias().addAll(Arrays.asList(c1, c2));
		p3.getCategorias().addAll(Arrays.asList(c1));

		repo.saveAll(Arrays.asList(c1, c2));

		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null, "São Paulo");

		Cidade cidade1 = new Cidade(null, "Uberlandia", e1);
		Cidade cidade2 = new Cidade(null, "São Paulo", e2);
		Cidade cidade3 = new Cidade(null, "Campinas", e2);

		e1.getCidades().addAll(Arrays.asList(cidade1));
		e2.getCidades().addAll(Arrays.asList(cidade2, cidade3));

		estadoRepository.saveAll(Arrays.asList(e1, e2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "60735090220", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("555555", "22222"));

		Endereco end1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, cidade1);
		Endereco end2 = new Endereco(null, "Aven Matos", "105", "Apto 303", "Centro", "38220834", cli1, cidade2);

		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));

	}

}