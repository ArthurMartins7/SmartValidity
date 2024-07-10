package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Categoria;
import model.entity.Corredor;
import model.seletor.CategoriaSeletor;
import model.seletor.CorredorSeletor;

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
	
	private String incluirFiltros(CategoriaSeletor seletor, String query) {
		boolean primeiro = true;
		
		if (seletor.getNomeCorredor() != null) {
			if (primeiro) {
				query += " WHERE ";
			} else {
				query += " AND ";
			}
			query += "upper(corredor.nome) LIKE UPPER('%" + seletor.getNomeCorredor() + "%')";
			primeiro = false;
		}

		if (seletor.getTipoCategoria() != null) {
			if (primeiro) {
				query += " WHERE ";
			} else {
				query += " AND ";
			}
			query += " upper(categoria.tipo) LIKE UPPER('%" + seletor.getTipoCategoria() + "%')";
		}

		return query;
	}

	public ArrayList<Categoria> consultarComFiltro(CategoriaSeletor seletor) {
		ArrayList<Categoria> categorias = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		ResultSet resultado = null;
		String q = "SELECT Categoria.* " + " FROM Categoria"
				+ " INNER JOIN Corredor on corredor.idCorredor = categoria.idCorredor ";

		q = incluirFiltros(seletor, q);

		if (seletor.temPaginacao()) {
			q += " LIMIT " + seletor.getLimite();
			q += " OFFSET " + seletor.getOffset();
		}

		try {
			resultado = stmt.executeQuery(q);

			while (resultado.next()) {
				Categoria c = new Categoria();

				c.setIdCategoria(resultado.getInt("idCategoria"));
				c.setTipo(resultado.getString("tipo"));
				CorredorRepository corredorRepository = new CorredorRepository();
				c.setCorredor(corredorRepository.consultarPorId(resultado.getInt("idCorredor")));
				categorias.add(c);
			}

		} catch (SQLException erro) {
			System.out.println("Erro ao consultar com filtro todos as CATEGORIAS");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return categorias;
	}

	public int contarTotalRegistros(CategoriaSeletor seletor) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		int totalRegistros = 0;
		ResultSet resultado = null;
		String query = " select COUNT(categoria.idCategoria) from categoria "
				+ " inner join corredor on corredor.IdCorredor = categoria.idCorredor ";

		query = incluirFiltros(seletor, query);

		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				totalRegistros = resultado.getInt(1);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao contar as categorias filtradas");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return totalRegistros;
	}

	public int contarPaginas(CategoriaSeletor seletor) {
		int totalPaginas = 0;
		int totalRegistros = this.contarTotalRegistros(seletor);

		totalPaginas = totalRegistros / seletor.getLimite();
		int resto = totalRegistros % seletor.getLimite();

		if (resto > 0) {
			totalPaginas++;
		}

		return totalPaginas;
	}

}
