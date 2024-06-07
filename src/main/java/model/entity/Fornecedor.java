package model.entity;

import java.util.List;

public class Fornecedor {

	private int idFornecedor;
	private String nome;
	private String telefone;
	private String cnpj;
	private List<Endereco> enderecos;

	public Fornecedor(int idFornecedor, String nome, String telefone, String cnpj, List<Endereco> enderecos) {
		super();
		this.idFornecedor = idFornecedor;
		this.nome = nome;
		this.telefone = telefone;
		this.cnpj = cnpj;
		this.enderecos = enderecos;
	}

	public Fornecedor() {
		super();

	}

	public int getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(int idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

}
