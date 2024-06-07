package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Categoria;
import model.entity.Corredor;

public class CategoriaRepository implements BaseRepository<Categoria> {

	@Override
	public Categoria salvar(Categoria novaCategoria) {
		Connection conexao = Banco.getConnection();
		String consulta = "INSERT INTO Categoria (tipo, idCorredor) VALUES (?, ?);";
		PreparedStatement ps = Banco.getPreparedStatementWithPk(conexao, consulta);
		
		try {
			ps.setString(1, novaCategoria.getTipo());
			ps.setInt(2, novaCategoria.getCorredor().getIdCorredor());
			ps.execute();
			ResultSet resultado = ps.getGeneratedKeys();
			if (resultado.next()) {
				novaCategoria.setIdCategoria(resultado.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao tentar salvar uma nova categoria");
			System.out.println("Erro: " + e);
		}
		return novaCategoria;
	}

	@Override
	public boolean excluir(int id) {
		Connection c = Banco.getConnection();
		Statement s = Banco.getStatement(c);
		String q = "DELETE FROM Categoria WHERE idCategoria = " + id +";";
		boolean excluiu = false;
		try {
			if(s.executeUpdate(q) == 1) {
				excluiu = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao tentar excluir uma Categoria");
			System.out.println("Erro: " + e);
		} finally {
			Banco.closePreparedStatement(s);
			Banco.closeConnection(c);
		}
		
		return excluiu;
	}

	@Override
	public boolean alterar(Categoria ce) {
		Connection c = Banco.getConnection();
		String q = "UPDATE Categoria SET tipo=?, idCorredor=? WHERE idCategoria=?;";
		PreparedStatement ps = Banco.getPreparedStatementWithPk(c, q);
		boolean alterou = false;
		
		try {
			ps.setString(1, ce.getTipo());
			ps.setInt(2, ce.getCorredor().getIdCorredor());
			ps.setInt(3, ce.getIdCategoria());
			
			if(ps.executeUpdate() == 1) {
				alterou = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao tentar alterar uma Categoria!");
			System.out.println("Erro: " + e);
		} finally {
			Banco.closePreparedStatement(ps);
			Banco.closeConnection(c);
		}
		return alterou;
	}

	@Override
	public Categoria consultarPorId(int id) {
		Connection conexao = Banco.getConnection();
		Statement stmt = Banco.getStatement(conexao);
		ResultSet resultado = null;
		String consulta = "SELECT idCategoria, tipo, idCorredor FROM Categoria WHERE idCategoria = " + id + ";";
		Categoria categoria = new Categoria();

		try {
			resultado = stmt.executeQuery(consulta);
			if (resultado.next()) {
				categoria.setIdCategoria(resultado.getInt("idCategoria"));
				categoria.setTipo(resultado.getString("tipo"));
				CorredorRepository corredorRepository = new CorredorRepository();
				categoria.setCorredor(corredorRepository.consultarPorId(resultado.getInt("idCorredor")));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao tentar consultar a categoria pelo id!");
			System.out.println("Erro" + e);
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conexao);
		}

		return categoria;
	}

	@Override
	public ArrayList<Categoria> consultarTodos() {
		ArrayList<Categoria> categorias = new ArrayList<>();
		Connection conexao = Banco.getConnection();
		Statement stmt = Banco.getStatement(conexao);
		ResultSet resultado = null;
		String consulta = "SELECT idCategoria, tipo, idCorredor FROM Categoria;";

		try {
			resultado = stmt.executeQuery(consulta);
			while (resultado.next()) {
				Categoria categoria = new Categoria();
				categoria.setIdCategoria(resultado.getInt("idCategoria"));
				categoria.setTipo(resultado.getString("tipo"));
				CorredorRepository corredorRepository = new CorredorRepository();
				categoria.setCorredor(corredorRepository.consultarPorId(resultado.getInt("idCorredor")));
				categorias.add(categoria);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao tentar consultar todas as categorias!");
			System.out.println("Erro" + e);
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conexao);
		}

		return categorias;
	}

}
