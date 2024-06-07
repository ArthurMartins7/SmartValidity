package service;

import model.entity.Endereco;
import model.repository.EnderecoRepository;

public class EnderecoService {
	
	EnderecoRepository r = new EnderecoRepository();
	
	public Endereco salvar(Endereco e) {
		return r.salvar(e);
	}
}
