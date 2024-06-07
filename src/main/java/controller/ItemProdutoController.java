package controller;

import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.ItemProduto;
import model.seletor.ItemProdutoSeletor;
import service.ItemProdutoService;

@Path("/itemProduto")
public class ItemProdutoController {

	ItemProdutoService service = new ItemProdutoService();
	
	@POST
	@Path("/filtro")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<ItemProduto> consultarComFiltros(ItemProdutoSeletor seletor){
		 return service.consultarComFiltros(seletor);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ItemProduto salvar(ItemProduto ip) {
		return service.salvar(ip);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean excluir(@PathParam("id") int id) {
		return service.excluir(id);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean alterar(ItemProduto ip) {
		return service.alterar(ip);
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

}
