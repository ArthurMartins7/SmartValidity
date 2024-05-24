package model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Produto {

	private int idProduto;
	private String descricao;
	private String marca;
	private String unidadeMedida;
	private Categoria categoria;
	private ArrayList<Fornecedor> fornecedores;

	public Produto(int idProduto, String descricao, String marca, String unidadeMedida, Categoria categoria,
			ArrayList<Fornecedor> fornecedores) {
		super();
		this.idProduto = idProduto;
		this.descricao = descricao;
		this.marca = marca;
		this.unidadeMedida = unidadeMedida;
		this.categoria = categoria;
		this.fornecedores = fornecedores;
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

}
