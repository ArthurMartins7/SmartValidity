package model.entity;

import java.time.LocalDate;

import model.entity.enums.PerfilAcesso;

public class Colaborador {

	private int id;
	private String cpf;
	private String nome;
	private String login;
	private String senha;
	private LocalDate dataNascimento;
	private PerfilAcesso perfil;
	private String idSessao;

	public Colaborador() {
		super();
	}

	public Colaborador(int id, String cpf, String nome, String login, String senha, LocalDate dataNascimento,
			PerfilAcesso perfil, String idSessao) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
		this.perfil = perfil;
		this.idSessao = idSessao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public PerfilAcesso getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilAcesso perfil) {
		this.perfil = perfil;
	}

	public String getIdSessao() {
		return idSessao;
	}

	public void setIdSessao(String idSessao) {
		this.idSessao = idSessao;
	}

}
