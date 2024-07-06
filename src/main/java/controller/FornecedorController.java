package controller;

import java.util.List;

import exception.SmartValidityException;
import filter.AuthFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import model.entity.Colaborador;
import model.entity.Fornecedor;
import model.entity.enums.PerfilAcesso;
import service.ColaboradorService;
import service.CorredorService;
import service.FornecedorService;

@Path("/restrito/fornecedor")
public class FornecedorController {

	@Context
	private HttpServletRequest request;
	String idSessaoNoHeader;

	CorredorService corredorService = new CorredorService();
	ColaboradorService colaboradorService = new ColaboradorService();

	FornecedorService service = new FornecedorService();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Fornecedor salvar(Fornecedor f) throws SmartValidityException {
		idSessaoNoHeader = request.getHeader(AuthFilter.CHAVE_ID_SESSAO);
		if (idSessaoNoHeader == null || idSessaoNoHeader.isEmpty()) {
			throw new SmartValidityException("Usuário sem permissão (idSessao não infomado)");
		}

		validarUsuarioAutenticado();

		return this.service.salvar(f);
	}

	@DELETE
	@Path("/{id}")
	public boolean excluir(@PathParam("id") int id) throws SmartValidityException {
		idSessaoNoHeader = request.getHeader(AuthFilter.CHAVE_ID_SESSAO);
		if (idSessaoNoHeader == null || idSessaoNoHeader.isEmpty()) {
			throw new SmartValidityException("Usuário sem permissão (idSessao não infomado)");
		}

		validarUsuarioAutenticado();

		return this.service.excluir(id);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean alterar(Fornecedor f) throws SmartValidityException {
		idSessaoNoHeader = request.getHeader(AuthFilter.CHAVE_ID_SESSAO);
		if (idSessaoNoHeader == null || idSessaoNoHeader.isEmpty()) {
			throw new SmartValidityException("Usuário sem permissão (idSessao não infomado)");
		}

		validarUsuarioAutenticado();

		return this.service.alterar(f);
	}

	@GET
	@Path("/todos")
	public List<Fornecedor> consultarTodos() throws SmartValidityException {
		idSessaoNoHeader = request.getHeader(AuthFilter.CHAVE_ID_SESSAO);
		if (idSessaoNoHeader == null || idSessaoNoHeader.isEmpty()) {
			throw new SmartValidityException("Usuário sem permissão (idSessao não infomado)");
		}

		validarUsuarioAutenticado();

		return this.service.consultarTodos();
	}

	@GET
	@Path("/{id}")
	public Fornecedor consultarPorId(@PathParam("id") int id) throws SmartValidityException {
		idSessaoNoHeader = request.getHeader(AuthFilter.CHAVE_ID_SESSAO);
		if (idSessaoNoHeader == null || idSessaoNoHeader.isEmpty()) {
			throw new SmartValidityException("Usuário sem permissão (idSessao não infomado)");
		}

		validarUsuarioAutenticado();

		return this.service.consultarPorId(id);
	}

	private void validarUsuarioAutenticado() throws SmartValidityException {

		Colaborador colaboradorAutenticado = this.colaboradorService.consultarPorIdSessao(this.idSessaoNoHeader);

		if (colaboradorAutenticado == null) {
			throw new SmartValidityException("Usuário não encontrado");
		}

		if (colaboradorAutenticado.getPerfil() != PerfilAcesso.GERENCIADOR
				&& colaboradorAutenticado.getIdSessao() != this.idSessaoNoHeader) {
			throw new SmartValidityException("Usuário sem permissão de acesso");
		}
	}

}
