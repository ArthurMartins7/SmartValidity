package controller;

import java.util.ArrayList;
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
import model.entity.Corredor;
import model.entity.ItemProduto;
import model.entity.enums.PerfilAcesso;
import model.seletor.CorredorSeletor;
import model.seletor.ItemProdutoSeletor;
import service.ColaboradorService;
import service.CorredorService;

@Path("/restrito/corredor")
public class CorredorController {

	@Context
	private HttpServletRequest request;
	String idSessaoNoHeader;

	CorredorService corredorService = new CorredorService();
	ColaboradorService colaboradorService = new ColaboradorService();

	@POST
	@Path("/filtro")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Corredor> consultarComFiltros(CorredorSeletor seletor) {
		return corredorService.consultarComFiltros(seletor);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Corredor salvar(Corredor corredor) throws SmartValidityException {
		idSessaoNoHeader = request.getHeader(AuthFilter.CHAVE_ID_SESSAO);
		if (idSessaoNoHeader == null || idSessaoNoHeader.isEmpty()) {
			throw new SmartValidityException("Usuário sem permissão (idSessao não infomado)");
		}

		validarUsuarioAutenticado();

//		Colaborador colaboradorAutenticado = this.colaboradorService.consultarPorIdSessao(idSessaoNoHeader);
//
//		if (colaboradorAutenticado == null) {
//			throw new SmartValidityException("Usuário não encontrado");
//		}
//
//		if (colaboradorAutenticado.getPerfil() != PerfilAcesso.GERENCIADOR
//				&& colaboradorAutenticado.getIdSessao() != idSessaoNoHeader) {
//			throw new SmartValidityException("Usuário sem permissão de acesso");
//		}

		return this.corredorService.salvar(corredor);
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean excluir(@PathParam("id") int id) throws SmartValidityException {
		idSessaoNoHeader = request.getHeader(AuthFilter.CHAVE_ID_SESSAO);
		if (idSessaoNoHeader == null || idSessaoNoHeader.isEmpty()) {
			throw new SmartValidityException("Usuário sem permissão (idSessao não infomado)");
		}

		validarUsuarioAutenticado();

		return this.corredorService.excluir(id);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean alterar(Corredor c) throws SmartValidityException {
		idSessaoNoHeader = request.getHeader(AuthFilter.CHAVE_ID_SESSAO);
		if (idSessaoNoHeader == null || idSessaoNoHeader.isEmpty()) {
			throw new SmartValidityException("Usuário sem permissão (idSessao não infomado)");
		}

		validarUsuarioAutenticado();

		return this.corredorService.alterar(c);
	}

	@Path("/todos")
	@GET
	public ArrayList<Corredor> consultarTodos() {

		return this.corredorService.consultarTodos();

	}

	@GET
	@Path("/{id}")
	public Corredor consultarPorId(@PathParam("id") int id) {
		return this.corredorService.consultarPorId(id);
	}

	@POST
	@Path("/contar")
	@Consumes(MediaType.APPLICATION_JSON)
	public int contarTotalRegistros(CorredorSeletor seletor) {
		return this.corredorService.contarTotalRegistros(seletor);
	}

	@POST
	@Path("/total-paginas")
	@Consumes(MediaType.APPLICATION_JSON)
	public int contarPaginas(CorredorSeletor seletor) {
		return this.corredorService.contarPaginas(seletor);
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
