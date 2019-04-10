package br.com.pdv.api.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.pdv.api.model.domain.Produto;

public class ProdutoDTO {

	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatorio")
	@Length(min = 5, max = 50, message = "tamanho deve ser entrew 5 e 50 caracteres")
	private String nome;

	private Double preco;

	public ProdutoDTO() {
	}

	public ProdutoDTO(Produto obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.preco = obj.getPreco();
	}

	public ProdutoDTO(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public static Produto fromDTO(ProdutoDTO dto) {
		return new Produto(dto.getId(), dto.getNome(), dto.getPreco());
	}

}
