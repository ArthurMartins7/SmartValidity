package service;

import java.util.List;

import model.entity.Colaborador;
import model.entity.enums.PerfilAcesso;
import model.repository.ColaboradorRepository;

public class ColaboradorService {

	private ColaboradorRepository repository = new ColaboradorRepository();

	public Colaborador salvar(Colaborador novoColaborador) {
		validarPerfilColaborador(novoColaborador);
		return repository.salvar(novoColaborador);
	}

	public boolean atualizar(Colaborador colaboradorEditado) {
		validarPerfilColaborador(colaboradorEditado);
		return repository.alterar(colaboradorEditado);
	}

	private void validarPerfilColaborador(Colaborador umColaborador) {
		if (umColaborador.getPerfil() == null) {
			umColaborador.setPerfil(PerfilAcesso.COLABORADOR);
		}
	}

	public boolean excluir(int id) {
		return repository.excluir(id);
	}

	public Colaborador consultarPorId(int id) {
		return repository.consultarPorId(id);
	}

	public List<Colaborador> consultarTodas() {
		return repository.consultarTodos();
	}

	public Colaborador consultarPorIdSessao(String idSessaoNoHeader) {
		return this.repository.consultarPorIdSessao(idSessaoNoHeader);
	}
}
