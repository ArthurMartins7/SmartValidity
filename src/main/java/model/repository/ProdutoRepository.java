package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.entity.Fornecedor;
import model.entity.Produto;

public class ProdutoRepository implements BaseRepository<Produto>{

	@Override
	public Produto salvar(Produto produto) {
		Connection c = Banco.getConnection();
		String q = "INSERT INTO Produto (descricao, marca, unidade_medida, quantidade, cod_barras, idCategoria)"
				+ " VALUES (?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = Banco.getPreparedStatementWithPk(c, q);
		
		try {
			ps.setString(1, produto.getDescricao());
			ps.setString(2, produto.getMarca());
			ps.setString(3, produto.getUnidadeMedida());
			ps.setInt(4, produto.getQuantidade());
			ps.setString(5, produto.getCodigoBarras());
			ps.setInt(6, produto.getCategoria().getIdCategoria());
			
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				produto.setIdProduto(rs.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao tentar salvar um novo produto!");
			System.out.println("Erro: " + e);
			e.printStackTrace();
		} finally {
			Banco.closePreparedStatement(ps);
			Banco.closeConnection(c);
		}
		return produto;
	}

	@Override
	public boolean excluir(int id) {
		Connection c = Banco.getConnection();
		Statement s = Banco.getStatement(c);
		String q = "DELETE FROM Produto WHERE idProduto = "+id+";";
		boolean excluiu = false;
		
		try {
			if(s.executeUpdate(q) == 1) {
				excluiu = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao tentar excluir um Produto!");
			System.out.println("Erro: " + e);
		} finally {
			Banco.closeStatement(s);
			Banco.closeConnection(c);
		}
		return excluiu;
	}

	@Override
	public boolean alterar(Produto pe) {
		Connection c = Banco.getConnection();
		String q = "UPDATE Produto SET "
				+ "descricao=?, "
				+ "marca=?, "
				+ "unidade_medida=?, "
				+ "quantidade=?, "
				+ "cod_barras=?, "
				+ "idCategoria=? "
				+ "WHERE idProduto=?;";
		
		PreparedStatement ps = Banco.getPreparedStatementWithPk(c, q);
		boolean alterou = false;
				
		try {
			ps.setString(1, pe.getDescricao());
			ps.setString(2, pe.getMarca());
			ps.setString(3, pe.getUnidadeMedida());
			ps.setInt(4, pe.getQuantidade());
			ps.setString(5, pe.getCodigoBarras());
			ps.setInt(6, pe.getCategoria().getIdCategoria());
			ps.setInt(7, pe.getIdProduto());
			
			if(ps.executeUpdate() == 1) {
				alterou = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao tentar alterar um Produto!");
			System.out.println("Erro: " + e);
		} finally {
			Banco.closePreparedStatement(ps);
			Banco.closeConnection(c);
		}
				
		return alterou;
	}

	@Override
	public Produto consultarPorId(int id) {
		Connection conexao = Banco.getConnection();
		Statement stmt = Banco.getStatement(conexao);
		ResultSet resultado = null;
		Produto produto = new Produto();
		String consulta = "SELECT idProduto, "
				+ "descricao, "
				+ "marca, "
				+ "unidade_medida, "
				+ "quantidade, "
				+ "cod_barras, "
				+ "idCategoria "
				+ "FROM Produto "
				+ "WHERE idProduto = " + id+";";
		
		try {
			resultado = stmt.executeQuery(consulta);
			if(resultado.next()) {
				produto.setIdProduto(resultado.getInt("idProduto"));
				produto.setDescricao(resultado.getString("descricao"));
				produto.setMarca(resultado.getString("marca"));
				produto.setUnidadeMedida(resultado.getString("unidade_medida"));
				produto.setQuantidade(resultado.getInt("quantidade"));
				produto.setCodigoBarras(resultado.getString("cod_barras"));
				CategoriaRepository categoriaRepository = new CategoriaRepository();
				produto.setCategoria(categoriaRepository.consultarPorId(resultado.getInt("idCategoria")));
				FornecedorRepository fornecedorRepository = new FornecedorRepository();
				produto.setFornecedores(fornecedorRepository.consultarFornecedoresPorIdProduto(resultado.getInt("idProduto")));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao tentar consultar produto pelo id!");
			System.out.println("Erro" + e);
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conexao);
		}
		
		return produto;
	}

	@Override
	public ArrayList<Produto> consultarTodos() {
		Connection conexao = Banco.getConnection();
		Statement stmt = Banco.getStatement(conexao);
		ResultSet resultado = null;
		ArrayList<Produto> produtos = new ArrayList<>();
		String consulta = "SELECT idProduto, "
				+ "descricao, "
				+ "marca, "
				+ "unidade_medida, "
				+ "quantidade, "
				+ "cod_barras, "
				+ "idCategoria "
				+ "FROM Produto;";
		
		try {
			resultado = stmt.executeQuery(consulta);
			while(resultado.next()) {
				Produto produto = new Produto();
				produto.setIdProduto(resultado.getInt("idProduto"));
				produto.setDescricao(resultado.getString("descricao"));
				produto.setMarca(resultado.getString("marca"));
				produto.setUnidadeMedida(resultado.getString("unidade_medida"));
				produto.setQuantidade(resultado.getInt("quantidade"));
				produto.setCodigoBarras(resultado.getString("cod_barras"));
				CategoriaRepository categoriaRepository = new CategoriaRepository();
				produto.setCategoria(categoriaRepository.consultarPorId(resultado.getInt("idCategoria")));
				FornecedorRepository fornecedorRepository = new FornecedorRepository();
				produto.setFornecedores(fornecedorRepository.consultarFornecedoresPorIdProduto(resultado.getInt("idProduto")));
				produtos.add(produto);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao tentar consultar todos os produtos!");
			System.out.println("Erro:" + e);
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conexao);
		}
		
	
		return produtos;
	}
	

}
