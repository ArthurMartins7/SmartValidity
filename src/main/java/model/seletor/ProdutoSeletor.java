package model.seletor;

public class ProdutoSeletor extends BaseSeletor {

	String codBarras;
	String nomeProduto;
	String marca;
	String tipoCategoria;
	String nomeCorredor;
	String unidadeMedida;
	int idUsuario;

	public ProdutoSeletor() {
		super();
	}

	public ProdutoSeletor(String codBarras, String nomeProduto, String marca, String tipoCategoria, String nomeCorredor,
			String unidadeMedida, int idUsuario) {
		super();
		this.codBarras = codBarras;
		this.nomeProduto = nomeProduto;
		this.marca = marca;
		this.tipoCategoria = tipoCategoria;
		this.nomeCorredor = nomeCorredor;
		this.unidadeMedida = unidadeMedida;
		this.idUsuario = idUsuario;
	}

	public String getCodBarras() {
		return codBarras;
	}

	public void setCodBarras(String codBarras) {
		this.codBarras = codBarras;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
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

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

}
