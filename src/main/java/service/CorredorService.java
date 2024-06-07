package service;

import java.util.ArrayList;

import model.entity.Corredor;
import model.repository.CorredorRepository;

public class CorredorService {

	CorredorRepository corredorRepository = new CorredorRepository();

	public Corredor salvar(Corredor corredor) {
		return corredorRepository.salvar(corredor);
	}

	public boolean excluir(int id) {
		return corredorRepository.excluir(id);
	}

	public boolean alterar(Corredor c) {
		return corredorRepository.alterar(c);
	}

	public ArrayList<Corredor> consultarTodos() {
		return corredorRepository.consultarTodos();
	}

	public Corredor consultarPorId(int id) {
		return corredorRepository.consultarPorId(id);
	}

}
