package model.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Categoria;
import model.entity.Corredor;

public class CategoriaRepository implements BaseRepository<Categoria> {

	@Override
	public Categoria salvar(Categoria novaEntidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean excluir(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(Categoria entidade) {
		// TODO Auto-generated method stub
		return false;
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
