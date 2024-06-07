package model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Produto {

	private int idProduto;
	private String descricao;
	private String marca;
	private String unidadeMedida;
	private int quantidade;
	private String codigoBarras;
	private Categoria categoria;
	private ArrayList<Fornecedor> fornecedores;
	private List<ItemProduto> itemProdutos;

	public Produto(int idProduto, String descricao, String marca, String unidadeMedida, int quantidade,
			String codigoBarras, Categoria categoria, ArrayList<Fornecedor> fornecedores,
			List<ItemProduto> itemProdutos) {
		super();
		this.idProduto = idProduto;
		this.descricao = descricao;
		this.marca = marca;
		this.unidadeMedida = unidadeMedida;
		this.quantidade = quantidade;
		this.codigoBarras = codigoBarras;
		this.categoria = categoria;
		this.fornecedores = fornecedores;
		this.itemProdutos = itemProdutos;
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

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public ArrayList<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(ArrayList<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public List<ItemProduto> getItemProdutos() {
		return itemProdutos;
	}

	public void setItemProdutos(List<ItemProduto> itemProdutos) {
		this.itemProdutos = itemProdutos;
	}

}
