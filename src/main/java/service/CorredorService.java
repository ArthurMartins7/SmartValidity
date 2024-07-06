package service;

import java.util.ArrayList;

import exception.SmartValidityException;
import filter.AuthFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.core.Context;
import model.entity.Colaborador;
import model.entity.Corredor;
import model.entity.enums.PerfilAcesso;
import model.repository.CorredorRepository;

public class CorredorService {

	CorredorRepository corredorRepository = new CorredorRepository();

	public Corredor salvar(Corredor corredor) {
		return corredorRepository.salvar(corredor);
	}

	public boolean excluir(int id) throws SmartValidityException {
		return this.corredorRepository.excluir(id);
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
