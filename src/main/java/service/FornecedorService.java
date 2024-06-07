package service;

import java.util.List;

import model.entity.Fornecedor;
import model.repository.FornecedorRepository;

public class FornecedorService {

	FornecedorRepository repository = new FornecedorRepository();

	public Fornecedor salvar(Fornecedor fornecedor) {
		return repository.salvar(fornecedor);
	}

	public boolean excluir(int id) {
		return repository.excluir(id);
	}

	public boolean alterar(Fornecedor f) {
		return repository.alterar(f);
	}

	public Fornecedor consultarPorId(int id) {
		return repository.consultarPorId(id);
	}

	public List<Fornecedor> consultarTodos() {
		return repository.consultarTodos();
	}

}
