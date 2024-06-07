package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entity.Fornecedor;

public class FornecedorRepository implements BaseRepository<Fornecedor> {

	@Override
	public Fornecedor salvar(Fornecedor fornecedor) {
		Connection c = Banco.getConnection();
		String q = "INSERT INTO Fornecedor (nome, telefone, cnpj) VALUES (?, ?, ?);";
		PreparedStatement ps = Banco.getPreparedStatementWithPk(c, q);
		
		try {
			ps.setString(1, fornecedor.getNome());
			ps.setString(2, fornecedor.getTelefone());
			ps.setString(3, fornecedor.getCnpj());
			
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				fornecedor.setIdFornecedor(rs.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao tentar salvar um novo fornecedor");
			System.out.println("Erro: " + e);
		} finally {
			Banco.closePreparedStatement(ps);
			Banco.closeConnection(c);
		}
		return fornecedor;
	}

	@Override
	public boolean excluir(int id) {
		Connection c = Banco.getConnection();
		Statement s = Banco.getStatement(c);
		String q = "DELETE FROM Fornecedor WHERE idFornecedor = " + id;
		boolean excluiu = false;
		
		try {
			if(s.executeUpdate(q) == 1) {
				excluiu = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao tentar excluir um fornecedor!");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(s);
			Banco.closeConnection(c);
		}
		return excluiu;
	}

	@Override
	public boolean alterar(Fornecedor fe) {
		Connection c = Banco.getConnection();
		String q = "UPDATE Fornecedor SET nome = ?, "
		        + "telefone = ?, "
		        + "cnpj = ? "
		        + "WHERE idFornecedor = ?;";


		PreparedStatement ps = Banco.getPreparedStatementWithPk(c, q);
		boolean alterou = false;
		
		
		try {
			ps.setString(1, fe.getNome());
			ps.setString(2, fe.getTelefone());
			ps.setString(3, fe.getCnpj());
			ps.setInt(4, fe.getIdFornecedor());
			
			if(ps.executeUpdate() == 1) {
				alterou = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao tentar alterar um Fornecedor!");
			System.out.println("Erro: " + e);
		}
		return alterou;
	}

	@Override
	public Fornecedor consultarPorId(int id) {
		Connection conexao = Banco.getConnection();
		Statement stmt = Banco.getStatement(conexao);
		ResultSet resultado = null;
		String consulta = "SELECT idFornecedor, nome, telefone, cnpj FROM Fornecedor WHERE idFornecedor = " + id +";";
		Fornecedor fornecedor = new Fornecedor();
		try {
			resultado = stmt.executeQuery(consulta);
			if(resultado.next()) {
				fornecedor.setIdFornecedor(resultado.getInt("idFornecedor"));
				fornecedor.setNome(resultado.getString("nome"));
				fornecedor.setTelefone(resultado.getString("telefone"));
				fornecedor.setCnpj(resultado.getString("cnpj"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao tentar consultar pelo ID");
			System.out.println("Erro: " + e);
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conexao);
		}
		
		return fornecedor;
	}

	@Override
	public ArrayList<Fornecedor> consultarTodos() {
		Connection conexao = Banco.getConnection();
		Statement stmt = Banco.getStatement(conexao);
		ResultSet resultado = null;
		String consulta = "SELECT idFornecedor, nome, telefone, cnpj FROM Fornecedor;";
		ArrayList<Fornecedor> fornecedores = new ArrayList<>();
		try {
			resultado = stmt.executeQuery(consulta);
			while(resultado.next()) {
				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setIdFornecedor(resultado.getInt("idFornecedor"));
				fornecedor.setNome(resultado.getString("nome"));
				fornecedor.setTelefone(resultado.getString("telefone"));
				fornecedor.setCnpj(resultado.getString("cnpj"));
				fornecedores.add(fornecedor);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao tentar consultar todos os fornecedores");
			System.out.println("Erro: " + e);
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conexao);
		}
		
		return fornecedores;
	}
	
	public ArrayList<Fornecedor> consultarFornecedoresPorIdProduto(int idProduto) {
		Connection conexao = Banco.getConnection();
		Statement stmt = Banco.getStatement(conexao);
		ResultSet resultado = null;
		ArrayList<Fornecedor> fornecedores = new ArrayList<>();
		String consulta = "SELECT \r\n"
				+ "	fornecedor.idFornecedor,\r\n"
				+ "    fornecedor.nome,\r\n"
				+ "    fornecedor.telefone,\r\n"
				+ "    fornecedor.cnpj\r\n"
				+ "   \r\n"
				+ "FROM\r\n"
				+ "	Fornecedor_Produto\r\n"
				+ "INNER JOIN\r\n"
				+ "	Fornecedor\r\n"
				+ "ON\r\n"
				+ "	fornecedor.idFornecedor = Fornecedor_Produto.idFornecedor\r\n"
				+ "INNER JOIN\r\n"
				+ "	Produto\r\n"
				+ "ON\r\n"
				+ "	produto.idProduto = Fornecedor_Produto.idProduto\r\n"
				+ "where\r\n"
				+ "	produto.idproduto = " + idProduto
				+ "; ";
		try {
			resultado = stmt.executeQuery(consulta);
			while(resultado.next()) {
				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setIdFornecedor(resultado.getInt("idFornecedor"));
				fornecedor.setNome(resultado.getString("nome"));
				fornecedor.setTelefone(resultado.getString("telefone"));
				fornecedor.setCnpj(resultado.getString("cnpj"));
				fornecedores.add(fornecedor);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao tentar consultar os fornecedores pelo id do produto");
			System.out.println("Erro: " + e);
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conexao);
		}
		return fornecedores;
		
	}

}
