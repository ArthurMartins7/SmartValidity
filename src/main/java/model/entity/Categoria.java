package model.entity;

public class Categoria {

	private int idCategoria;
	private String tipo;
	private Corredor corredor;

	public Categoria(int idCategoria, String tipo, Corredor corredor) {
		super();
		this.idCategoria = idCategoria;
		this.tipo = tipo;
		this.corredor = corredor;
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

}
