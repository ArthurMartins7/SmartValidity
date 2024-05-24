package model.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import model.entity.ItemProduto;

public class ItemProdutoRepository implements BaseRepository<ItemProduto> {

	@Override
	public ItemProduto salvar(ItemProduto novaEntidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean excluir(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(ItemProduto entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItemProduto consultarPorId(int id) {
		Connection conexao = Banco.getConnection();
		Statement stmt = Banco.getStatement(conexao);
		ResultSet resultado = null;
		ItemProduto itemProduto = new ItemProduto();
		String consulta = "SELECT * FROM Item_Produto WHERE idItem_Produto = " + id + ";";
		try {
			resultado = stmt.executeQuery(consulta);
			if (resultado.next()) {

				itemProduto.setIdItemProduto(resultado.getInt("idItem_Produto"));
				itemProduto.setQuantidade(resultado.getInt("quantidade"));
				itemProduto.setLote(resultado.getString("lote"));
				itemProduto.setPrecoCompra(resultado.getDouble("preco_compra"));
				itemProduto.setPrecoVenda(resultado.getDouble("preco_venda"));

				// Obter o Timestamp do ResultSet
				Timestamp timestampDF = resultado.getTimestamp("data_fabricacao");

				if (timestampDF != null) {
					// Convertendo o Timestamp para LocalDateTime
					LocalDateTime dataFabricacao = timestampDF.toLocalDateTime();

					// Atribuindo a dataFabricacao convertida ao produto
					itemProduto.setDataFabricacao(dataFabricacao);
				}
				itemProduto.setDataVencimento(resultado.getDate("data_vencimento").toLocalDate());
				Timestamp timestampDR = resultado.getTimestamp("data_recebimento");

				if (timestampDR != null) {
					LocalDateTime dataRecebimento = timestampDR.toLocalDateTime();
					itemProduto.setDataRecebimento(dataRecebimento);
				}
				ProdutoRepository produtoRepository = new ProdutoRepository();
				itemProduto.setProduto(produtoRepository.consultarPorId(resultado.getInt("idProduto")));

			}
		} catch (SQLException e) {
			System.out.println("Erro ao tentar consultar o item produto pelo id!");
			System.out.println("Erro: " + e);
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conexao);
		}
		return itemProduto;
	}

	@Override
	public ArrayList<ItemProduto> consultarTodos() {
		Connection conexao = Banco.getConnection();
		Statement stmt = Banco.getStatement(conexao);
		ResultSet resultado = null;
		ArrayList<ItemProduto> itensProdutos = new ArrayList<>();
		String consulta = "SELECT * FROM Item_Produto;";
		try {
			resultado = stmt.executeQuery(consulta);
			while (resultado.next()) {
				ItemProduto itemProduto = new ItemProduto();
				itemProduto.setIdItemProduto(resultado.getInt("idItem_Produto"));
				itemProduto.setQuantidade(resultado.getInt("quantidade"));
				itemProduto.setLote(resultado.getString("lote"));
				itemProduto.setPrecoCompra(resultado.getDouble("preco_compra"));
				itemProduto.setPrecoVenda(resultado.getDouble("preco_venda"));

				// Obter o Timestamp do ResultSet
				Timestamp timestampDF = resultado.getTimestamp("data_fabricacao");

				if (timestampDF != null) {
					// Convertendo o Timestamp para LocalDateTime
					LocalDateTime dataFabricacao = timestampDF.toLocalDateTime();

					// Atribuindo a dataFabricacao convertida ao produto
					itemProduto.setDataFabricacao(dataFabricacao);
				}
				itemProduto.setDataVencimento(resultado.getDate("data_vencimento").toLocalDate());
				Timestamp timestampDR = resultado.getTimestamp("data_recebimento");

				if (timestampDR != null) {
					LocalDateTime dataRecebimento = timestampDR.toLocalDateTime();
					itemProduto.setDataRecebimento(dataRecebimento);
				}
				ProdutoRepository produtoRepository = new ProdutoRepository();
				itemProduto.setProduto(produtoRepository.consultarPorId(resultado.getInt("idProduto")));
				itensProdutos.add(itemProduto);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao tentar consultar todos os itens produtos!");
			System.out.println("Erro: " + e);
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conexao);
		}

		return itensProdutos;
	}

}
