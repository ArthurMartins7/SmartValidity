package service;

import java.util.List;

import exception.SmartValidityException;
import model.entity.Categoria;
import model.entity.Corredor;
import model.repository.CategoriaRepository;
import model.repository.ProdutoRepository;
import model.seletor.CategoriaSeletor;
import model.seletor.CorredorSeletor;

public class CategoriaService {

	CategoriaRepository repository = new CategoriaRepository();
	ProdutoRepository produtoRepository = new ProdutoRepository();

	public Categoria salvar(Categoria categoria) {

		return repository.salvar(categoria);
	}
	
	public boolean excluir(int id) throws SmartValidityException {
	    boolean possuiProdutos = produtoRepository.verificarProdutosPorCategoria(id);

	    if (possuiProdutos) {
	        throw new SmartValidityException("Não é possível excluir esta categoria pois existem produtos associados a ela."); 
	    }
		return repository.excluir(id);
	}
	
	public boolean alterar(Categoria c) {
		return repository.alterar(c);
	}

	public List<Categoria> consultarTodas() {
		return repository.consultarTodos();
	}

	public Categoria ConsultarPorId(int id) {
		return repository.consultarPorId(id);

	}
	
	public Categoria consultarPorId(int id) {
		return repository.consultarPorId(id);
	}
	
	public List<Categoria> consultarComFiltros(CategoriaSeletor seletor) {
		return repository.consultarComFiltro(seletor);
	}
	
	public int contarPaginas(CategoriaSeletor seletor) {
		return this.repository.contarPaginas(seletor);
	}
	
	public int contarTotalRegistros(CategoriaSeletor seletor) {
		return this.repository.contarTotalRegistros(seletor);
	}

}