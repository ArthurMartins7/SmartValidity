package service;

import java.util.List;

import exception.SmartValidityException;
import model.entity.Categoria;
import model.entity.Produto;
import model.repository.ItemProdutoRepository;
import model.repository.ProdutoRepository;
import model.seletor.CategoriaSeletor;
import model.seletor.ProdutoSeletor;

public class ProdutoService {

	ProdutoRepository repository = new ProdutoRepository();
	ItemProdutoRepository itemProdutoRepository = new ItemProdutoRepository();

	public Produto salvar(Produto produto) {
		 
		return repository.salvar(produto);
	}

	public boolean excluir(int id) throws SmartValidityException {
		boolean possuiItemProdutos = itemProdutoRepository.verificarItemProdutosPorProduto(id);

	    if (possuiItemProdutos) {
	        throw new SmartValidityException("Não é possível excluir este produto pois existem item produtos associados a ele."); 
	    }
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
