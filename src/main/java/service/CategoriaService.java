package service;

import java.util.List;

import model.entity.Categoria;
import model.repository.CategoriaRepository;

public class CategoriaService {

	CategoriaRepository repository = new CategoriaRepository();

	public List<Categoria> consultarTodas() {
		return repository.consultarTodos();
	}

	public Categoria ConsultarPorId(int id) {
		return repository.consultarPorId(id);

	}
}