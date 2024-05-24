package model.repository;

import java.sql.Connection;
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
	public Produto salvar(Produto novaEntidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean excluir(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(Produto entidade) {
		// TODO Auto-generated method stub
		return false;
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
