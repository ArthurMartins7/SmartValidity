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

import model.entity.ItemProduto;
import model.seletor.ItemProdutoSeletor;

public class ItemProdutoRepository implements BaseRepository<ItemProduto> {

	@Override
	public ItemProduto salvar(ItemProduto itemProduto) {
		Connection c = Banco.getConnection();
		String q = "INSERT INTO item_produto (lote, preco_compra, preco_venda, data_fabricacao, data_vencimento, data_recebimento, idProduto)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?);";
		
		PreparedStatement ps = Banco.getPreparedStatementWithPk(c, q);
		
		try {
			ps.setString(1, itemProduto.getLote());
			ps.setDouble(2, itemProduto.getPrecoCompra());
			ps.setDouble(3, itemProduto.getPrecoVenda());
			ps.setObject(4, itemProduto.getDataFabricacao());
			ps.setObject(5, itemProduto.getDataVencimento());
			ps.setObject(6, itemProduto.getDataRecebimento());
			ps.setInt(7, itemProduto.getProduto().getIdProduto());
			
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				itemProduto.setIdItemProduto(rs.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao tentar salvar um novo item_produto");
			System.out.println("Erro: " + e);
		} finally {
			Banco.closePreparedStatement(ps);
			Banco.closeConnection(c);
		}
		return itemProduto;
	}

	@Override
	public boolean excluir(int id) {
		Connection c = Banco.getConnection();
		Statement s = Banco.getStatement(c);
		String q = "DELETE FROM Item_Produto WHERE idItem_produto = "+id+";";
		boolean  excluiu= false;
		
		try {
			if(s.executeUpdate(q) == 1) {
				excluiu = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao tentar excluir um item_produto");
			System.out.println("Erro: " + e);
		} finally {
			Banco.closeStatement(s);
			Banco.closeConnection(c);
		}
		return excluiu;
	}

	@Override
	public boolean alterar(ItemProduto ipe) {
		Connection c = Banco.getConnection();
		String q = "UPDATE Item_Produto "
				+ "SET lote=?, "
				+ "preco_compra=?, "
				+ "preco_venda=?, "
				+ "data_fabricacao=?, "
				+ "data_vencimento=?, "
				+ "data_recebimento=?, "
				+ "idProduto =? "
				+ "WHERE idItem_Produto =?;";
		PreparedStatement ps = Banco.getPreparedStatementWithPk(c, q);
		boolean alterou = false;
		
		try {
			ps.setString(1, ipe.getLote());
			ps.setDouble(2, ipe.getPrecoCompra());
			ps.setDouble(3, ipe.getPrecoVenda());
			ps.setObject(4, ipe.getDataFabricacao());
			ps.setObject(5, ipe.getDataVencimento());
			ps.setObject(6, ipe.getDataRecebimento());
			ps.setInt(7, ipe.getProduto().getIdProduto());
			ps.setInt(8, ipe.getIdItemProduto());
			
			if(ps.executeUpdate() == 1) {
				alterou = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao tentar alterar um Item_Produto");
			System.out.println("Erro: " + e);
		} finally {
			Banco.closePreparedStatement(ps);
			Banco.closeConnection(c);
		}
		return alterou;
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
	
	private String incluirFiltros(ItemProdutoSeletor seletor, String query) {
		boolean primeiro = true;
		if(seletor.getNomeProduto() != null) {
			if(primeiro) {
				query += " WHERE ";
			}else {
				query += " AND ";
			}
			query += "upper(p.descricao) LIKE UPPER('%" + seletor.getNomeProduto() + "%')";
			primeiro = false;
		}
		
		if(seletor.getMarca() != null) {
			if(primeiro) {
				query += " WHERE ";
			}else {
				query += " AND ";
			}
			query += " upper(p.marca) LIKE UPPER('%" + seletor.getMarca() + "%')";
		}
		
		if(seletor.getCodigoBarras() != null) {
			if(primeiro) {
				query += " WHERE ";
			}else {
				query += " AND ";
			}
			query += " upper(p.cod_barras) LIKE UPPER('%" + seletor.getCodigoBarras() + "%')";
		}
		
		if(seletor.getLote() != null) {
			if(primeiro) {
				query += " WHERE ";
			}else {
				query += " AND ";
			}
			query += " upper(ip.lote) LIKE UPPER('%" + seletor.getLote() + "%')";
		}
		
		if(seletor.getNomeCorredor() != null) {
			if(primeiro) {
				query += " WHERE ";
			}else {
				query += " AND ";
			}
			query += " upper(corr.nome) LIKE UPPER('%" + seletor.getNomeCorredor() + "%')";
		}
		
		if(seletor.getNomeCategoria() != null) {
			if(primeiro) {
				query += " WHERE ";
			}else {
				query += " AND ";
			}
			query += " upper(c.tipo) LIKE UPPER('%" + seletor.getNomeCategoria() + "%')";
		}
		
		if ((seletor.getDataInicioFabricacao() != null) & (seletor.getDataFinalFabricacao() != null)) {
			//Regra composta, olha as 3 opções de preenchimento do período
			
			//Todo o período preenchido (ínicio e fim)
			// WHERE DATADEINICIODAPESQUISA BETWEEN INICIO AND FIM
			if(primeiro) {
				query += " WHERE ";
			}else {
				query += " AND ";
			}
			query += " data_fabricacao BETWEEN '" + seletor.getDataInicioFabricacao() + "' AND '" + seletor.getDataFinalFabricacao() + "'";
		}
		
		if ((seletor.getDataInicioVencimento() != null) & (seletor.getDataFinalVencimento() != null)) {
			
			if(primeiro) {
				query += " WHERE ";
			}else {
				query += " AND ";
			}
			query += " data_vencimento BETWEEN '" + seletor.getDataInicioVencimento() + "' AND '" + seletor.getDataFinalVencimento() + "'";
		}
		
		if ((seletor.getDataInicioRecebimento() != null) & (seletor.getDataFinalRecebimento() != null)) {
			
			if(primeiro) {
				query += " WHERE ";
			}else {
				query += " AND ";
			}
			query += " data_recebimento BETWEEN '" + seletor.getDataInicioRecebimento() + "' AND '" + seletor.getDataFinalRecebimento() + "'";
		}
		
		
		return query;
	}
	
	
	public ArrayList<ItemProduto> consultarComFiltro(ItemProdutoSeletor seletor) {
		ArrayList<ItemProduto> itemProdutos = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ResultSet resultado = null;
		String q = "SELECT ip.* "
				+ " FROM Item_Produto ip "
				+ " JOIN "
				+ " Produto p ON ip.idProduto = p.idProduto "
				+ " JOIN "
				+ " Categoria c ON p.idCategoria = c.idCategoria "
				+ " JOIN "
				+ " Corredor corr ON c.idCorredor = corr.idCorredor ";
		
		q = incluirFiltros(seletor, q);
		
		if(seletor.temPaginacao()) {
			q += " LIMIT " + seletor.getLimite();
			q += " OFFSET " + seletor.getOffset();
		}
		
		try {
			resultado = stmt.executeQuery(q);
			
			while(resultado.next()){
				ItemProduto ip = new ItemProduto();
				
				ip.setIdItemProduto(resultado.getInt("idItem_produto"));
				ip.setLote(resultado.getString("lote"));
				ip.setPrecoCompra(resultado.getDouble("preco_compra"));
				ip.setPrecoVenda(resultado.getDouble("preco_venda"));
				
				// Obter o Timestamp do ResultSet
				Timestamp timestampDF = resultado.getTimestamp("data_fabricacao");

				if (timestampDF != null) {
					// Convertendo o Timestamp para LocalDateTime
					LocalDateTime dataFabricacao = timestampDF.toLocalDateTime();

					// Atribuindo a dataFabricacao convertida ao produto
					ip.setDataFabricacao(dataFabricacao);
				}
				ip.setDataVencimento(resultado.getDate("data_vencimento").toLocalDate());
				Timestamp timestampDR = resultado.getTimestamp("data_recebimento");

				if (timestampDR != null) {
					LocalDateTime dataRecebimento = timestampDR.toLocalDateTime();
					ip.setDataRecebimento(dataRecebimento);
				}
				ProdutoRepository produtoRepository = new ProdutoRepository();
				ip.setProduto(produtoRepository.consultarPorId(resultado.getInt("idProduto")));
				
				itemProdutos.add(ip);
				
			}
		} catch  (SQLException erro){
			System.out.println("Erro ao consultar com filtro todas os ITENS_PRODUTO");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return itemProdutos;
	}
	
	public int contarTotalRegistros(ItemProdutoSeletor seletor) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		int totalRegistros = 0;
		ResultSet resultado = null;
		String query = " select COUNT(ip.idItem_produto) from item_produto ip "
					 + " inner join produto p on ip.idProduto = p.idProduto "
					 + " inner join categoria c on c.idCategoria = p.idCategoria "
					 + " inner join corredor corr on corr.idCorredor = c.idCorredor  ";
		
		query = incluirFiltros(seletor, query);
		
		try{
			resultado = stmt.executeQuery(query);
			if(resultado.next()){
				totalRegistros = resultado.getInt(1);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao contar os item_produtos filtrados");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return totalRegistros;
	}
	
	
	public int contarPaginas(ItemProdutoSeletor seletor) {
		int totalPaginas = 0;
		int totalRegistros = this.contarTotalRegistros(seletor);	
		
		totalPaginas =  totalRegistros / seletor.getLimite();
		int resto = totalRegistros % seletor.getLimite(); 
		
		if(resto > 0) {
			totalPaginas++;
		}
		
		return totalPaginas;
	}

}
