package model.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Corredor;

public class CorredorRepository implements BaseRepository<Corredor> {

	@Override
	public Corredor salvar(Corredor novaEntidade) {
		return null;
	}

	@Override
	public boolean excluir(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(Corredor entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Corredor consultarPorId(int id) {
		Connection conexao = Banco.getConnection();
		Statement stmt = Banco.getStatement(conexao);
		ResultSet resultado = null;
		Corredor corredor = new Corredor();
		String consulta = "SELECT idCorredor, nome, responsavel FROM Corredor WHERE idCorredor = " + id + ";";
		try {
			resultado = stmt.executeQuery(consulta);
			if (resultado.next()) {

				corredor.setIdCorredor(resultado.getInt("idCorredor"));
				corredor.setNome(resultado.getString("nome"));
				corredor.setResponsavel(resultado.getString("responsavel"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao tentar consultar corredor pelo id!");
			System.out.println("Erro:" + e);
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conexao);
		}

		return corredor;
	}

	@Override
	public ArrayList<Corredor> consultarTodos() {
		ArrayList<Corredor> corredores = new ArrayList<>();
		Connection conexao = Banco.getConnection();
		Statement stmt = Banco.getStatement(conexao);
		ResultSet resultado = null;

		String consulta = "SELECT idCorredor, nome, responsavel from Corredor;";

		try {
			resultado = stmt.executeQuery(consulta);
			while (resultado.next()) {
				Corredor corredor = new Corredor();
				corredor.setIdCorredor(resultado.getInt("idCorredor"));
				corredor.setNome(resultado.getString("nome"));
				corredor.setResponsavel(resultado.getString("responsavel"));
				corredores.add(corredor);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar todos os corredores.");
			System.out.println("Erro: " + erro);
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conexao);
		}

		return corredores;
	}

}
