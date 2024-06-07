package model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ItemProduto {

	private int idItemProduto;
	private String lote;
	private double precoCompra;
	private double precoVenda;
	private LocalDateTime dataFabricacao;
	private LocalDateTime dataRecebimento;
	private LocalDate dataVencimento;
	private Produto produto;

	public ItemProduto(int idItemProduto, String lote, double precoCompra, double precoVenda,
			LocalDateTime dataFabricacao, LocalDateTime dataRecebimento, LocalDate dataVencimento, Produto produto) {
		super();
		this.idItemProduto = idItemProduto;
		this.lote = lote;
		this.precoCompra = precoCompra;
		this.precoVenda = precoVenda;
		this.dataFabricacao = dataFabricacao;
		this.dataRecebimento = dataRecebimento;
		this.dataVencimento = dataVencimento;
		this.produto = produto;
	}

	public ItemProduto() {
		super();

	}

	public int getIdItemProduto() {
		return idItemProduto;
	}

	public void setIdItemProduto(int idItemProduto) {
		this.idItemProduto = idItemProduto;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public double getPrecoCompra() {
		return precoCompra;
	}

	public void setPrecoCompra(double precoCompra) {
		this.precoCompra = precoCompra;
	}

	public double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public LocalDateTime getDataFabricacao() {
		return dataFabricacao;
	}

	public void setDataFabricacao(LocalDateTime dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}

	public LocalDateTime getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(LocalDateTime dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
