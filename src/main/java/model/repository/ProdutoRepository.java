package model.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
				+ "preco_compra, "
				+ "preco_venda, "
				+ "unidade_medida, "
				+ "data_vencimento, "
				+ "data_fabricacao, "
				+ "quantidade, "
				+ "lote, "
				+ "idCategoria "
				+ "FROM Produto "
				+ "WHERE idProduto = " + id+";";
		
		try {
			resultado = stmt.executeQuery(consulta);
			if(resultado.next()) {
				produto.setIdProduto(resultado.getInt("idProduto"));
				produto.setDescricao(resultado.getString("descricao"));
				produto.setMarca(resultado.getString("marca"));
				produto.setPreco_compra(resultado.getDouble("preco_compra"));
				produto.setPreco_venda(resultado.getDouble("preco_venda"));
				produto.setUnidade_medida(resultado.getString("unidade_medida"));
				produto.setData_vencimento(resultado.getDate("data_vencimento").toLocalDate());
				
				//Obter o Timestamp do ResultSet
				Timestamp timestamp = resultado.getTimestamp("data_fabricacao");
				
				if(timestamp != null) {
				//Convertendo o Timestamp para LocalDateTime
				LocalDateTime dataFabricacao = timestamp.toLocalDateTime();
				
				//Atribuindo a dataFabricacao convertida ao produto
				produto.setData_fabricacao(dataFabricacao);
				}
				
				produto.setQuantidade(resultado.getInt("quantidade"));
				produto.setLote(resultado.getString("lote"));
				CategoriaRepository categoriaRepository = new CategoriaRepository();
				produto.setCategoria(categoriaRepository.consultarPorId(resultado.getInt("idCategoria")));
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
				+ "preco_compra, "
				+ "preco_venda, "
				+ "unidade_medida, "
				+ "data_vencimento, "
				+ "data_fabricacao, "
				+ "quantidade, "
				+ "lote, "
				+ "idCategoria "
				+ "FROM Produto;";
		
		try {
			resultado = stmt.executeQuery(consulta);
			while(resultado.next()) {
				Produto produto = new Produto();
				produto.setIdProduto(resultado.getInt("idProduto"));
				produto.setDescricao(resultado.getString("descricao"));
				produto.setMarca(resultado.getString("marca"));
				produto.setPreco_compra(resultado.getDouble("preco_compra"));
				produto.setPreco_venda(resultado.getDouble("preco_venda"));
				produto.setUnidade_medida(resultado.getString("unidade_medida"));
				produto.setData_vencimento(resultado.getDate("data_vencimento").toLocalDate());
				
				Timestamp timestamp = resultado.getTimestamp("data_fabricacao");
				LocalDateTime dataFabricacao = timestamp.toLocalDateTime();
				produto.setData_fabricacao(dataFabricacao);
				
				produto.setQuantidade(resultado.getInt("quantidade"));
				produto.setLote(resultado.getString("lote"));
				
				CategoriaRepository categoriaRepository = new CategoriaRepository();
				produto.setCategoria(categoriaRepository.consultarPorId(resultado.getInt("idCategoria")));
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
