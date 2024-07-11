package service;

import java.util.List;

import model.entity.Categoria;
import model.entity.Produto;
import model.repository.ProdutoRepository;
import model.seletor.CategoriaSeletor;
import model.seletor.ProdutoSeletor;

public class ProdutoService {

	ProdutoRepository repository = new ProdutoRepository();

	public Produto salvar(Produto produto) {

		return repository.salvar(produto);
	}

	public boolean excluir(int id) {
		return repository.excluir(id);
	}

	public boolean alterar(Produto p) {
		return repository.alterar(p);
	}

	public List<Produto> consultarTodos() {
		return repository.consultarTodos();
	}

	public Produto consultarPorId(int id) {
		return repository.consultarPorId(id);
	}
	
	public List<Produto> consultarComFiltros(ProdutoSeletor seletor) {
		return repository.consultarComFiltro(seletor);
	}
	
	public int contarPaginas(ProdutoSeletor seletor) {
		return this.repository.contarPaginas(seletor);
	}
	
	public int contarTotalRegistros(ProdutoSeletor seletor) {
		return this.repository.contarTotalRegistros(seletor);
	}

}
