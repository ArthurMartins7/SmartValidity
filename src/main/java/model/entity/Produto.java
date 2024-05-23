package model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Produto {

	private int idProduto;
	private String descricao;
	private String marca;
	private double preco_compra;
	private double preco_venda;
	private String unidade_medida;
	private LocalDate data_vencimento;
	private LocalDateTime data_fabricacao;
	private int quantidade;
	private String lote;
	private Categoria categoria;

	public Produto(int idProduto, String descricao, String marca, double preco_compra, double preco_venda, String unidade_medida,
			LocalDate data_vencimento, LocalDateTime data_fabricacao, int quantidade, String lote, Categoria categoria) {
		super();
		this.idProduto = idProduto;
		this.descricao = descricao;
		this.marca = marca;
		this.preco_compra = preco_compra;
		this.preco_venda = preco_venda;
		this.unidade_medida = unidade_medida;
		this.data_vencimento = data_vencimento;
		this.data_fabricacao = data_fabricacao;
		this.quantidade = quantidade;
		this.lote = lote;
		this.categoria = categoria;
	}

	public Produto() {
		super();
		
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public double getPreco_compra() {
		return preco_compra;
	}

	public void setPreco_compra(double preco_compra) {
		this.preco_compra = preco_compra;
	}

	public double getPreco_venda() {
		return preco_venda;
	}

	public void setPreco_venda(double preco_venda) {
		this.preco_venda = preco_venda;
	}

	public String getUnidade_medida() {
		return unidade_medida;
	}

	public void setUnidade_medida(String unidade_medida) {
		this.unidade_medida = unidade_medida;
	}

	public LocalDate getData_vencimento() {
		return data_vencimento;
	}

	public void setData_vencimento(LocalDate data_vencimento) {
		this.data_vencimento = data_vencimento;
	}

	public LocalDateTime getData_fabricacao() {
		return data_fabricacao;
	}

	public void setData_fabricacao(LocalDateTime data_fabricacao) {
		this.data_fabricacao = data_fabricacao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
