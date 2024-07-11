package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.SmartValidityException;
import filter.AuthFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.core.Context;
import model.entity.Colaborador;
import model.entity.Corredor;
import model.entity.ItemProduto;
import model.entity.enums.PerfilAcesso;
import model.repository.CategoriaRepository;
import model.repository.CorredorRepository;
import model.seletor.CorredorSeletor;
import model.seletor.ItemProdutoSeletor;

public class CorredorService {

	CategoriaRepository categoriaRepository = new CategoriaRepository();
	CorredorRepository corredorRepository = new CorredorRepository();

	public Corredor salvar(Corredor corredor) {
		return corredorRepository.salvar(corredor);
	}

	public boolean excluir(int id) throws SmartValidityException {
		 // Verificar se existem categorias associadas a este corredor
	    boolean possuiCategorias = categoriaRepository.verificarCategoriasPorCorredor(id);

	    if (possuiCategorias) {
	        throw new SmartValidityException("Não é possível excluir este corredor pois existem categorias associadas a ele.");
	        // Ou você pode retornar false se preferir:
	        // return false;
	    }

	    // Se não houver categorias associadas, procede com a exclusão do corredor
		return this.corredorRepository.excluir(id);
	}

	public boolean alterar(Corredor c) {
		return corredorRepository.alterar(c);
	}

	public ArrayList<Corredor> consultarTodos() {
		return corredorRepository.consultarTodos();
	}

	public Corredor consultarPorId(int id) {
		return corredorRepository.consultarPorId(id);
	}
	
	public List<Corredor> consultarComFiltros(CorredorSeletor seletor) {
		return corredorRepository.consultarComFiltro(seletor);
	}
	
	public int contarPaginas(CorredorSeletor seletor) {
		return this.corredorRepository.contarPaginas(seletor);
	}
	
	public int contarTotalRegistros(CorredorSeletor seletor) {
		return this.corredorRepository.contarTotalRegistros(seletor);
	}

}
