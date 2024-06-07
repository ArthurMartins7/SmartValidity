package model.entity;

import java.util.ArrayList;
import java.util.List;

public class Corredor {

	private int idCorredor;
	private String nome;
	private String responsavel;
	private List<Categoria> categorias;

	public Corredor(int idCorredor, String nome, String responsavel, List<Categoria> categorias) {
		super();
		this.idCorredor = idCorredor;
		this.nome = nome;
		this.responsavel = responsavel;
		this.categorias = categorias;
	}

	public Corredor() {
		super();

	}

	public int getIdCorredor() {
		return idCorredor;
	}

	public void setIdCorredor(int idCorredor) {
		this.idCorredor = idCorredor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategoria(List<Categoria> categorias) {
		this.categorias = categorias;
	}

}
