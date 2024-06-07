package service;

import java.util.List;

import model.entity.Categoria;
import model.repository.CategoriaRepository;

public class CategoriaService {

	CategoriaRepository repository = new CategoriaRepository();

	public Categoria salvar(Categoria categoria) {

		return repository.salvar(categoria);
	}
	
	public boolean excluir(int id) {
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
}