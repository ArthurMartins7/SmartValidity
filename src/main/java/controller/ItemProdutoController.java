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
import model.entity.ItemProduto;
import model.entity.enums.PerfilAcesso;
import model.seletor.ItemProdutoSeletor;
import service.ColaboradorService;
import service.CorredorService;
import service.ItemProdutoService;

@Path("/restrito/itemProduto")
public class ItemProdutoController {

	@Context
	private HttpServletRequest request;
	String idSessaoNoHeader;

	CorredorService corredorService = new CorredorService();
	ColaboradorService colaboradorService = new ColaboradorService();

	ItemProdutoService service = new ItemProdutoService();

	@POST
	@Path("/filtro")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<ItemProduto> consultarComFiltros(ItemProdutoSeletor seletor) {
		return service.consultarComFiltros(seletor);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ItemProduto salvar(ItemProduto ip) throws SmartValidityException {
		idSessaoNoHeader = request.getHeader(AuthFilter.CHAVE_ID_SESSAO);
		if (idSessaoNoHeader == null || idSessaoNoHeader.isEmpty()) {
			throw new SmartValidityException("Usuário sem permissão (idSessao não infomado)");
		}

		validarUsuarioAutenticado();

		return this.service.salvar(ip);
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
	public boolean alterar(ItemProduto ip) throws SmartValidityException {
		idSessaoNoHeader = request.getHeader(AuthFilter.CHAVE_ID_SESSAO);
		if (idSessaoNoHeader == null || idSessaoNoHeader.isEmpty()) {
			throw new SmartValidityException("Usuário sem permissão (idSessao não infomado)");
		}

		validarUsuarioAutenticado();

		return this.service.alterar(ip);
	}

	@GET
	@Path("/todos")
	public List<ItemProduto> consultarTodos() {
		return service.consultarTodos();

	}

	@GET
	@Path("/{id}")
	public ItemProduto consultarPorId(@PathParam("id") int id) {
		return service.consultarTodos(id);

	}

	@POST
	@Path("/contar")
	@Consumes(MediaType.APPLICATION_JSON)
	public int contarTotalRegistros(ItemProdutoSeletor seletor) {
		return this.service.contarTotalRegistros(seletor);
	}

	@POST
	@Path("/total-paginas")
	@Consumes(MediaType.APPLICATION_JSON)
	public int contarPaginas(ItemProdutoSeletor seletor) {
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
