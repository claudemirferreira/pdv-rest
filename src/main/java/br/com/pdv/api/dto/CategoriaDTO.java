package br.com.pdv.api.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.pdv.api.model.domain.Categoria;

public class CategoriaDTO {

	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatorio")
	@Length(min = 5, max = 50, message = "tamanho deve ser entrew 5 e 50 caracteres")
	private String nome;

	public CategoriaDTO() {
	}

	public CategoriaDTO(Categoria obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
	}

	public CategoriaDTO(Integer id, String nome) {
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

	public static Categoria fromDTO(CategoriaDTO dto) {
		return new Categoria(dto.getId(), dto.getNome());
	}

}
