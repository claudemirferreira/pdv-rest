package br.com.pdv.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.pdv.api.model.domain.Cidade;
import br.com.pdv.api.model.domain.Cliente;
import br.com.pdv.api.model.domain.Endereco;
import br.com.pdv.api.model.domain.enumerado.TipoCliente;

public class ClienteNewDTO {

	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatorio")
	@Length(min = 5, max = 100, message = "tamanho deve ser entrew 5 e 100 caracteres")
	private String nome;

	@NotEmpty(message = "Preenchimento obrigatorio")
	@Email(message = "email invalido")
	private String email;

	private String cpfOuCnpj;

	private Integer tipo;

	private String logradouro;

	private String numero;

	private String complemento;

	private String bairro;

	private String cep;

	private String telefone1;

	private String telefone2;

	private String telefone3;

	private Integer cidadeId;

	public ClienteNewDTO() {
	}

	public ClienteNewDTO(Cliente obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
	}

	public ClienteNewDTO(Integer id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}

	public static Cliente fromDTO(ClienteNewDTO dto) {
		Cliente c = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpfOuCnpj(),
				TipoCliente.toEnum(dto.getTipo()));
		Cidade cidade = new Cidade(dto.getCidadeId());
		
		System.out.println("cidade = " + cidade.getId());
		Endereco end = new Endereco(null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(), dto.getBairro(),
				dto.getCep(), c, cidade);

		c.getEnderecos().add(end);
		c.getTelefones().add(dto.getTelefone1());
		
		if(dto.getTelefone2() != null)
			c.getTelefones().add(dto.getTelefone2());
		if(dto.getTelefone3() != null)
			c.getTelefones().add(dto.getTelefone3());

		return c;
	}

}
