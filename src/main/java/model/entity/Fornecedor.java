package model.entity;

public class Fornecedor {

	private int idFornecedor;
	private String nome;
	private String telefone;
	private String cnpj;

	public Fornecedor(int idFornecedor, String nome, String telefone, String cnpj) {
		super();
		this.idFornecedor = idFornecedor;
		this.nome = nome;
		this.telefone = telefone;
		this.cnpj = cnpj;
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

}
