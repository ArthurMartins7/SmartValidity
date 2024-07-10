package model.seletor;

public class CategoriaSeletor extends BaseSeletor {

	String tipoCategoria;
	String nomeCorredor;
	int idUsuario;

	public CategoriaSeletor() {
	}

	public CategoriaSeletor(String tipoCategoria, String nomeCorredor, int idUsuario) {
		super();
		this.tipoCategoria = tipoCategoria;
		this.nomeCorredor = nomeCorredor;
		this.idUsuario = idUsuario;
	}

	public String getTipoCategoria() {
		return tipoCategoria;
	}

	public void setTipoCategoria(String tipoCategoria) {
		this.tipoCategoria = tipoCategoria;
	}

	public String getNomeCorredor() {
		return nomeCorredor;
	}

	public void setNomeCorredor(String nomeCorredor) {
		this.nomeCorredor = nomeCorredor;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

}
