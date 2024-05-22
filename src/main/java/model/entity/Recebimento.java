package model.entity;

import java.time.LocalDateTime;

public class Recebimento {

	private int idRecebimento;
	private LocalDateTime data_recebimento;
	private Fornecedor fornecedor;
	private Produto produto;

	public Recebimento(int idRecebimento, LocalDateTime data_recebimento, Fornecedor fornecedor, Produto produto) {
		super();
		this.idRecebimento = idRecebimento;
		this.data_recebimento = data_recebimento;
		this.fornecedor = fornecedor;
		this.produto = produto;
	}

	public Recebimento() {
		super();
		
	}

	public int getIdRecebimento() {
		return idRecebimento;
	}

	public void setIdRecebimento(int idRecebimento) {
		this.idRecebimento = idRecebimento;
	}

	public LocalDateTime getData_recebimento() {
		return data_recebimento;
	}

	public void setData_recebimento(LocalDateTime data_recebimento) {
		this.data_recebimento = data_recebimento;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
