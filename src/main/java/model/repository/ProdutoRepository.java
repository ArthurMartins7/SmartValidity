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

import model.entity.Categoria;
import model.entity.Fornecedor;
import model.entity.Produto;
import model.seletor.CategoriaSeletor;
import model.seletor.ItemProdutoSeletor;
import model.seletor.ProdutoSeletor;

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
	
	private String incluirFiltros(ProdutoSeletor seletor, String query) {
		boolean primeiro = true;
		
		if(seletor.getCodBarras() != null) {
			if(primeiro) {
				query += " WHERE ";
			}else {
				query += " AND ";
			}
			query += " upper(produto.cod_barras) LIKE UPPER('%" + seletor.getCodBarras() + "%')";
		}
		
		if(seletor.getNomeProduto() != null) {
			if(primeiro) {
				query += " WHERE ";
			}else {
				query += " AND ";
			}
			query += "upper(produto.descricao) LIKE UPPER('%" + seletor.getNomeProduto() + "%')";
			primeiro = false;
		}
		
		if(seletor.getMarca() != null) {
			if(primeiro) {
				query += " WHERE ";
			}else {
				query += " AND ";
			}
			query += " upper(produto.marca) LIKE UPPER('%" + seletor.getMarca() + "%')";
		}
		
		if(seletor.getTipoCategoria() != null) {
			if(primeiro) {
				query += " WHERE ";
			}else {
				query += " AND ";
			}
			query += " upper(categoria.tipo) LIKE UPPER('%" + seletor.getTipoCategoria() + "%')";
		}
		
		if(seletor.getNomeCorredor() != null) {
			if(primeiro) {
				query += " WHERE ";
			}else {
				query += " AND ";
			}
			query += " upper(corredor.nome) LIKE UPPER('%" + seletor.getNomeCorredor() + "%')";
		}
		
		if(seletor.getUnidadeMedida() != null) {
			if(primeiro) {
				query += " WHERE ";
			}else {
				query += " AND ";
			}
			query += " upper(produto.unidade_medida) LIKE UPPER('%" + seletor.getUnidadeMedida() + "%')";
		}
		
		return query;
	}
	
	public ArrayList<Produto> consultarComFiltro(ProdutoSeletor seletor) {
		ArrayList<Produto> produtos = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		ResultSet resultado = null;
		String q = "SELECT Produto.* " + " FROM Produto "
				+ " INNER JOIN Categoria on categoria.idCategoria = produto.idCategoria "
		 		+ " inner join Corredor on corredor.idCorredor = categoria.idCorredor ";

		q = incluirFiltros(seletor, q);

		if (seletor.temPaginacao()) {
			q += " LIMIT " + seletor.getLimite();
			q += " OFFSET " + seletor.getOffset();
		}

		try {
			resultado = stmt.executeQuery(q);

			while (resultado.next()) {
				Produto p = new Produto();

				p.setIdProduto(resultado.getInt("idProduto"));
				p.setDescricao(resultado.getString("descricao"));
				p.setMarca(resultado.getString("marca"));
				p.setUnidadeMedida(resultado.getString("unidade_medida"));
				p.setQuantidade(resultado.getInt("quantidade"));
				p.setCodigoBarras(resultado.getString("cod_barras"));
				CategoriaRepository categoriaRepository = new CategoriaRepository();
				p.setCategoria(categoriaRepository.consultarPorId(resultado.getInt("idCategoria")));
				produtos.add(p);
			}

		} catch (SQLException erro) {
			System.out.println("Erro ao consultar com filtro todos os PRODUTOS");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return produtos;
	}
	
	public int contarTotalRegistros(ProdutoSeletor seletor) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		int totalRegistros = 0;
		ResultSet resultado = null;
		String query = " select COUNT(produto.idProduto) from produto "
					 + " inner join categoria on categoria.idCategoria = produto.idCategoria "
					 + " inner join corredor on corredor.idCorredor = categoria.idCorredor ";
		
		query = incluirFiltros(seletor, query);
		
		try{
			resultado = stmt.executeQuery(query);
			if(resultado.next()){
				totalRegistros = resultado.getInt(1);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao contar os produtos filtrados");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return totalRegistros;
	}
	
	public int contarPaginas(ProdutoSeletor seletor) {
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
