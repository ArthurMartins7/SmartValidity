package service;


import java.util.List;

import model.entity.Fornecedor;
import model.repository.FornecedorRepository;

public class FornecedorService {
	
	FornecedorRepository repository = new FornecedorRepository();
	
	public Fornecedor consultarPorId(int id) {
		return repository.consultarPorId(id);
	}
	
	public List<Fornecedor> consultarTodos() {
		return repository.consultarTodos();
	}

}
