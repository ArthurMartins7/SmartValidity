package service;

import java.util.List;

import model.entity.ItemProduto;
import model.repository.ItemProdutoRepository;
import model.seletor.ItemProdutoSeletor;

public class ItemProdutoService {

	ItemProdutoRepository repository = new ItemProdutoRepository();

	public ItemProduto salvar(ItemProduto itemProduto) {

		return repository.salvar(itemProduto);
	}

	public boolean excluir(int id) {
		return repository.excluir(id);
	}

	public boolean alterar(ItemProduto ip) {
		return repository.alterar(ip);
	}

	public ItemProduto consultarTodos(int id) {
		return repository.consultarPorId(id);

	}

	public List<ItemProduto> consultarTodos() {
		return repository.consultarTodos();

	}
	
	public List<ItemProduto> consultarComFiltros(ItemProdutoSeletor seletor) {
		return repository.consultarComFiltro(seletor);
	}
	
	public int contarPaginas(ItemProdutoSeletor seletor) {
		return this.repository.contarPaginas(seletor);
	}
	
	public int contarTotalRegistros(ItemProdutoSeletor seletor) {
		return this.repository.contarTotalRegistros(seletor);
	}

}
