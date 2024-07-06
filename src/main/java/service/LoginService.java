package service;

import java.util.UUID;

import exception.SmartValidityException;
import model.dto.UsuarioDTO;
import model.entity.Colaborador;
import model.repository.ColaboradorRepository;

public class LoginService {
	
private ColaboradorRepository repository = new ColaboradorRepository();
	
	public Colaborador autenticar(UsuarioDTO usuarioDTO) throws SmartValidityException {
		
		if(usuarioDTO == null || 
			(usuarioDTO.getLogin() == null || usuarioDTO.getLogin().trim().isEmpty())) {
			throw new SmartValidityException("Usuário não informado");
		}
		
		if(usuarioDTO.getSenha() == null || usuarioDTO.getSenha().trim().isEmpty()) {
			throw new SmartValidityException("Senha não informada");
		}
		
		Colaborador colaboradorAutenticado = repository.consultarPorLoginSenha(usuarioDTO);
		
		if(colaboradorAutenticado == null) {
			throw new SmartValidityException("Login ou senha inválidos, tente novamente");
		}

		String idSessao = UUID.randomUUID().toString();
		colaboradorAutenticado.setIdSessao(idSessao);
		repository.alterarIdSessao(colaboradorAutenticado);
		
		return colaboradorAutenticado;
	}

	public boolean chaveValida(String idSessao) {
		Colaborador colaborador = this.repository.consultarPorIdSessao(idSessao);

		return colaborador != null 
				&& colaborador.getIdSessao() != null
				&& colaborador.getIdSessao().equals(idSessao);
	}

}
