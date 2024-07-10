package model.seletor;

public class CorredorSeletor extends BaseSeletor {

	private String nomeCorredor;
	private String nomeResponsavel;
	private Integer idUsuario;

	public CorredorSeletor(String nomeCorredor, String nomeResponsavel, Integer idUsuario) {
		super();
		this.nomeCorredor = nomeCorredor;
		this.nomeResponsavel = nomeResponsavel;
		this.idUsuario = idUsuario;
	}

	public CorredorSeletor() {

	}

	public String getNomeCorredor() {
		return nomeCorredor;
	}

	public void setNomeCorredor(String nomeCorredor) {
		this.nomeCorredor = nomeCorredor;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

}
