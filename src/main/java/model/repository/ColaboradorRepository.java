package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.dto.UsuarioDTO;
import model.entity.Colaborador;
import model.entity.enums.PerfilAcesso;
import util.StringUtils;

public class ColaboradorRepository implements BaseRepository<Colaborador> {

	@Override
	public Colaborador salvar(Colaborador novoColaborador) {
		String q = "INSERT INTO Colaborador (cpf, nome, login, senha, perfil_acesso, data_nascimento) VALUES (?, ?, ?, ?, ?, ?)";
		Connection c = Banco.getConnection();
		PreparedStatement ps = Banco.getPreparedStatementWithPk(c, q);
		try {
			preencherParametrosParaInsertOuUpdate(ps, novoColaborador);

			ps.execute();
			ResultSet resultado = ps.getGeneratedKeys();

			if (resultado.next()) {
				novoColaborador.setId(resultado.getInt(1));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao salvar novo colaborador");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(ps);
			Banco.closeConnection(c);
		}

		return novoColaborador;

	}

	private void preencherParametrosParaInsertOuUpdate(PreparedStatement ps, Colaborador novoColaborador)
			throws SQLException {
		ps.setString(1, novoColaborador.getCpf());
		ps.setString(2, novoColaborador.getNome());
		ps.setString(3, novoColaborador.getLogin());
		ps.setString(4, StringUtils.cifrar(novoColaborador.getSenha()));
		ps.setString(5, novoColaborador.getPerfil().toString());
		ps.setObject(6, novoColaborador.getDataNascimento());

	}

	@Override
	public boolean excluir(int id) {
		Connection c = Banco.getConnection();
		Statement s = Banco.getStatement(c);
		boolean excluiu = false;
		String query = "DELETE FROM Colaborador WHERE id = " + id;
		try {
			if(s.executeUpdate(query) == 1) {
				excluiu = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao excluir colaborador");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(s);
			Banco.closeConnection(c);
		}
		return excluiu;
	}

	@Override
	public boolean alterar(Colaborador colaborador) {
		Connection c = Banco.getConnection();
		String q = " UPDATE Colaborador "
					 + " SET   cpf=?, nome=?, login=?, senha=?, perfil_acesso=?,"
					 + "       data_nascimento=?, id_sessao=? "
				     + " WHERE id=?";
		
		PreparedStatement ps = Banco.getPreparedStatement(c, q);
		
		boolean alterou = false;
		
		try {
			this.preencherParametrosParaInsertOuUpdate(ps, colaborador);
			
			
			ps.setString(7, colaborador.getIdSessao());
			ps.setInt(8, colaborador.getId());
			
			if(ps.executeUpdate() == 1) {
				alterou = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao atualizar colaborador");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(ps);
			Banco.closeConnection(c);
		}
		return alterou;
	}
	
	public boolean alterarIdSessao(Colaborador novoColaborador) {
		boolean alterou = false;
		String q = " UPDATE Colaborador "
					 + " SET   id_sessao=? "
				     + " WHERE id=?";
		Connection c = Banco.getConnection();
		PreparedStatement ps = Banco.getPreparedStatement(c, q);
		try {
			ps.setString(1, novoColaborador.getIdSessao());
			ps.setInt(2, novoColaborador.getId());
			
			alterou = ps.executeUpdate() > 0;
		} catch (SQLException erro) {
			System.out.println("Erro ao atualizar idSessao do colaborador");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(ps);
			Banco.closeConnection(c);
		}
		return alterou;
	}
	

	@Override
	public Colaborador consultarPorId(int id) {
		Connection c = Banco.getConnection();
		Statement s = Banco.getStatement(c);
		
		ResultSet resultado = null;
		Colaborador colaborador = new Colaborador();
		String q = " SELECT * FROM Colaborador WHERE id = " + id;
		
		try{
			resultado = s.executeQuery(q);
			if(resultado.next()){
				colaborador = converterResultSetParaColaborador(resultado);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar colaborador com id (" + id + ")");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(s);
			Banco.closeConnection(c);
		}
		return colaborador;
	}

	@Override
	public ArrayList<Colaborador> consultarTodos() {
		ArrayList<Colaborador> colaboradores = new ArrayList<>();
		Connection c = Banco.getConnection();
		Statement s = Banco.getStatement(c);
		
		ResultSet resultado = null;
		String q = " SELECT * FROM Colaborador ";
		
		try{
			resultado = s.executeQuery(q);
			while(resultado.next()){
				Colaborador colaborador = this.converterResultSetParaColaborador(resultado);
				colaboradores.add(colaborador);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar todos os colaboradores");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(s);
			Banco.closeConnection(c);
		}
		return colaboradores;
	}
	
	private Colaborador converterResultSetParaColaborador(ResultSet resultado) throws SQLException {
		Colaborador colaborador = new Colaborador();
		colaborador.setId(Integer.parseInt(resultado.getString("ID")));
		colaborador.setCpf(resultado.getString("CPF"));
		colaborador.setNome(resultado.getString("NOME"));
		colaborador.setLogin(resultado.getString("LOGIN"));
		colaborador.setDataNascimento(resultado.getDate("DATA_NASCIMENTO").toLocalDate()); 
		colaborador.setPerfil(PerfilAcesso.valueOf(resultado.getString("PERFIL_ACESSO")));
		colaborador.setIdSessao(resultado.getString("ID_SESSAO"));
		
		return colaborador;
	}
	
	/**
	 * Referência: https://www.baeldung.com/sha-256-hashing-java
	 * @param usuarioDTO dto com login e senha informados
	 * @return Colaborador encontrado no banco
	 */
	public Colaborador consultarPorLoginSenha(UsuarioDTO usuarioDTO) {
		Connection c = Banco.getConnection();
		Statement s = Banco.getStatement(c);
		
		ResultSet resultado = null;
		Colaborador colaborador = null;
		String q = " SELECT * FROM Colaborador "
				     + " WHERE login = '" + usuarioDTO.getLogin() + "'"
				     + " AND senha = '" + StringUtils.cifrar(usuarioDTO.getSenha()) + "'";
		try{
			resultado = s.executeQuery(q);
			if(resultado.next()){
				colaborador = this.converterResultSetParaColaborador(resultado);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar colaborador com login (" + usuarioDTO.getLogin() + ")");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(s);
			Banco.closeConnection(c);
		}
		return colaborador;
	}

	public Colaborador consultarPorLogin(String login) {
		Connection c = Banco.getConnection();
		Statement s = Banco.getStatement(c);
		
		ResultSet resultado = null;
		Colaborador colaborador = new Colaborador();
		String q = " SELECT * FROM Colaborador "
				     + " WHERE login = '" + login + "'";
		try{
			resultado = s.executeQuery(q);
			if(resultado.next()){
				colaborador = this.converterResultSetParaColaborador(resultado);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar colaborador com login (" + login + ")");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(s);
			Banco.closeConnection(c);
		}
		return colaborador;
	}
	
	public Colaborador consultarPorIdSessao(String idSessao) {
		Connection c = Banco.getConnection();
		Statement s = Banco.getStatement(c);
		
		ResultSet resultado = null;
		Colaborador colaborador = new Colaborador();
		String q = " SELECT * FROM Colaborador "
				     + " WHERE id_sessao = '" + idSessao + "'";
		try{
			resultado = s.executeQuery(q);
			if(resultado.next()){
				colaborador = this.converterResultSetParaColaborador(resultado);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar colaborador com idSessao (" + idSessao + ")");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(s);
			Banco.closeConnection(c);
		}
		return colaborador;

}
	
}
