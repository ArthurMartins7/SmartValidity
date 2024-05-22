package model.entity;

public class Corredor {

	private int idCorredor;
	private String nome;
	private String responsavel;

	public Corredor(int idCorredor, String nome, String responsavel) {
		super();
		this.idCorredor = idCorredor;
		this.nome = nome;
		this.responsavel = responsavel;
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

}
