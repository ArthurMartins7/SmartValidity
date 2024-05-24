package service;

import java.util.List;

import model.entity.ItemProduto;
import model.repository.ItemProdutoRepository;

public class ItemProdutoService {

	ItemProdutoRepository repository = new ItemProdutoRepository();

	public ItemProduto consultarTodos(int id) {
		return repository.consultarPorId(id);

	}

	public List<ItemProduto> consultarTodos() {
		return repository.consultarTodos();

	}

}
