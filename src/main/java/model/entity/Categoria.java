package model.entity;

import java.util.List;

public class Categoria {

	private int idCategoria;
	private String tipo;
	private Corredor corredor;
	private List<Produto> produtos;

	public Categoria(int idCategoria, String tipo, Corredor corredor, List<Produto> produtos) {
		super();
		this.idCategoria = idCategoria;
		this.tipo = tipo;
		this.corredor = corredor;
		this.produtos = produtos;
	}

	public Categoria() {
		super();

	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Corredor getCorredor() {
		return corredor;
	}

	public void setCorredor(Corredor corredor) {
		this.corredor = corredor;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

}
