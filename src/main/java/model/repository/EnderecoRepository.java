package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.Endereco;

public class EnderecoRepository implements BaseRepository<Endereco>{

	@Override
	public Endereco salvar(Endereco e) {
		Connection c = Banco.getConnection();
		String q = "INSERT INTO Endereco (pais, estado, cidade, bairro, rua, numero, complemento, cep, idFornecedor) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = Banco.getPreparedStatementWithPk(c, q);
		
		try {
			ps.setString(1, e.getPais());
			ps.setString(2, e.getEstado());
			ps.setString(3, e.getCidade());
			ps.setString(4, e.getBairro());
			ps.setString(5, e.getRua());
			ps.setInt(6, e.getNumero());
			ps.setString(7, e.getComplemento());
			ps.setString(8, e.getCep());
			ps.setInt(9, e.getFornecedor().getIdFornecedor());
			
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				e.setIdEndereco(rs.getInt(1));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao tentar salvar um novo Endereço!");
			System.out.println("Erro: " + erro);
		}
		
		return e;
	}

	@Override
	public boolean excluir(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(Endereco entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Endereco consultarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Endereco> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
