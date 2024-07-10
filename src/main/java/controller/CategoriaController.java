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
import model.entity.Categoria;
import model.entity.Colaborador;
import model.entity.Corredor;
import model.entity.enums.PerfilAcesso;
import model.seletor.CategoriaSeletor;
import model.seletor.CorredorSeletor;
import service.CategoriaService;
import service.ColaboradorService;
import service.CorredorService;

@Path("/restrito/categoria")
public class CategoriaController {

	@Context
	private HttpServletRequest request;
	String idSessaoNoHeader;

	CorredorService corredorService = new CorredorService();
	ColaboradorService colaboradorService = new ColaboradorService();

	CategoriaService service = new CategoriaService();

	@POST
	@Path("/filtro")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Categoria> consultarComFiltros(CategoriaSeletor seletor) {
		return this.service.consultarComFiltros(seletor);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Categoria salvar(Categoria c) throws SmartValidityException {
		idSessaoNoHeader = request.getHeader(AuthFilter.CHAVE_ID_SESSAO);
		if (idSessaoNoHeader == null || idSessaoNoHeader.isEmpty()) {
			throw new SmartValidityException("Usuário sem permissão (idSessao não infomado)");
		}

		validarUsuarioAutenticado();

		return this.service.salvar(c);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
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
	public boolean alterar(Categoria c) throws SmartValidityException {
		idSessaoNoHeader = request.getHeader(AuthFilter.CHAVE_ID_SESSAO);
		if (idSessaoNoHeader == null || idSessaoNoHeader.isEmpty()) {
			throw new SmartValidityException("Usuário sem permissão (idSessao não infomado)");
		}

		validarUsuarioAutenticado();

		return this.service.alterar(c);
	}

	@GET
	@Path("/todas")
	public List<Categoria> consultarTodos() {
		return this.service.consultarTodas();

	}

	@GET
	@Path("/{id}")
	public Categoria consultarPorId(@PathParam("id") int id) {
		return this.service.ConsultarPorId(id);
	}

	@POST
	@Path("/contar")
	@Consumes(MediaType.APPLICATION_JSON)
	public int contarTotalRegistros(CategoriaSeletor seletor) {
		return this.service.contarTotalRegistros(seletor);
	}

	@POST
	@Path("/total-paginas")
	@Consumes(MediaType.APPLICATION_JSON)
	public int contarPaginas(CategoriaSeletor seletor) {
		return this.service.contarPaginas(seletor);
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
