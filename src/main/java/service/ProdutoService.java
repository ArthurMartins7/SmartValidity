package service;

import java.util.List;

import model.entity.Produto;
import model.repository.ProdutoRepository;

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

}
