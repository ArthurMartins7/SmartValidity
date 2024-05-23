package service;

import java.util.ArrayList;

import model.entity.Corredor;
import model.repository.CorredorRepository;

public class CorredorService {

	CorredorRepository corredorRepository = new CorredorRepository();

	public ArrayList<Corredor> consultarTodos() {

		return corredorRepository.consultarTodos();

	}
	
	public Corredor consultarPorId(int id) {
		
		return corredorRepository.consultarPorId(id);
		
	}

}
