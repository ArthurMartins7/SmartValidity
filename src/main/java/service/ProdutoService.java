package service;

import java.util.List;

import model.entity.Produto;
import model.repository.ProdutoRepository;

public class ProdutoService {

	ProdutoRepository repository = new ProdutoRepository();

	public List<Produto> consultarTodos() {
		return repository.consultarTodos();
	}

	public Produto consultarPorId(int id) {
		return repository.consultarPorId(id);
	}

}
