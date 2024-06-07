package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Corredor;

public class CorredorRepository implements BaseRepository<Corredor> {

	@Override
	public Corredor salvar(Corredor corredor) {
		Connection c = Banco.getConnection();
		String q = "INSERT INTO Corredor (nome, responsavel) VALUES (?,?)";
		
		PreparedStatement ps = Banco.getPreparedStatementWithPk(c, q);
		
		try {
			ps.setString(1, corredor.getNome());
			ps.setString(2, corredor.getResponsavel());
			
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				corredor.setIdCorredor(rs.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao tentar salvar um novo corredor!");
			System.out.println("Erro: " + e);
		} finally {
			Banco.closePreparedStatement(ps);
			Banco.closeConnection(c);
		}
		return corredor;
	}

	@Override
	public boolean excluir(int id) {
		Connection c = Banco.getConnection();
		Statement s = Banco.getStatement(c);
		boolean excluiu = false;
		String q = "DELETE FROM Corredor WHERE idCorredor = " + id;
		
		try {
			if(s.executeUpdate(q) == 1) {
				excluiu = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao tentar excluir um Corredor!");
			System.out.println("Erro: " + e);
			
		} finally {
			Banco.closeStatement(s);
			Banco.closeConnection(c);
		}
;
		return excluiu;
	}

	@Override
	public boolean alterar(Corredor ce) {
		Connection c = Banco.getConnection();
		String q = "UPDATE Corredor SET nome=?, responsavel=? "
				+ "WHERE idCorredor =?";
		PreparedStatement ps = Banco.getPreparedStatementWithPk(c, q);
		boolean alterou = false;
		
		try {
			ps.setString(1, ce.getNome());
			ps.setString(2, ce.getResponsavel());
			ps.setInt(3, ce.getIdCorredor());
			
			if(ps.executeUpdate() == 1) {
				alterou = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao tentar alterar um Corredor!");
			System.out.println("Erro: " + e);
		} finally {
			Banco.closePreparedStatement(ps);
			Banco.closeConnection(c);
		}
		return alterou;
	}

	@Override
	public Corredor consultarPorId(int id) {
		Connection conexao = Banco.getConnection();
		Statement stmt = Banco.getStatement(conexao);
		ResultSet resultado = null;
		Corredor corredor = new Corredor();
		String consulta = "SELECT idCorredor, nome, responsavel FROM Corredor WHERE idCorredor = " + id + ";";
		try {
			resultado = stmt.executeQuery(consulta);
			if (resultado.next()) {

				corredor.setIdCorredor(resultado.getInt("idCorredor"));
				corredor.setNome(resultado.getString("nome"));
				corredor.setResponsavel(resultado.getString("responsavel"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao tentar consultar corredor pelo id!");
			System.out.println("Erro:" + e);
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conexao);
		}

		return corredor;
	}

	@Override
	public ArrayList<Corredor> consultarTodos() {
		ArrayList<Corredor> corredores = new ArrayList<>();
		Connection conexao = Banco.getConnection();
		Statement stmt = Banco.getStatement(conexao);
		ResultSet resultado = null;

		String consulta = "SELECT idCorredor, nome, responsavel from Corredor;";

		try {
			resultado = stmt.executeQuery(consulta);
			while (resultado.next()) {
				Corredor corredor = new Corredor();
				corredor.setIdCorredor(resultado.getInt("idCorredor"));
				corredor.setNome(resultado.getString("nome"));
				corredor.setResponsavel(resultado.getString("responsavel"));
				corredores.add(corredor);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar todos os corredores.");
			System.out.println("Erro: " + erro);
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conexao);
		}

		return corredores;
	}

}
